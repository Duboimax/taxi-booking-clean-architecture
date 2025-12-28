package com.taxibooking.services;

import com.taxibooking.config.BookingStatus;
import com.taxibooking.config.TaxiStatus;
import com.taxibooking.dto.BookingRequest;
import com.taxibooking.dto.BookingResponse;
import com.taxibooking.exceptions.BusinessException;
import com.taxibooking.exceptions.ResourceNotFoundException;
import com.taxibooking.models.Booking;

import com.taxibooking.models.Customer;
import com.taxibooking.models.Taxi;
import com.taxibooking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TaxiService taxiService;
    private final CustomerService customerService;
    private final PricingService pricingService;
    private final NotificationService notificationService;
    private final ValidationService validationService;

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        Taxi taxi = taxiService.findTaxiById(request.getTaxiId());
        validateTaxiAvailability(taxi);

        Customer customer = customerService.findCustomerById(request.getCustomerId());

        Date pickupTime = new Date(request.getPickupTime());
        double calculatedPrice = pricingService.calculatePrice(taxi.getPrice(), pickupTime);

        Booking booking = buildBooking(taxi, customer, pickupTime, calculatedPrice);

        taxi.setStatus(TaxiStatus.BOOKED);

        Booking savedBooking = bookingRepository.save(booking);

        return convertToResponse(savedBooking);
    }

    public List<BookingResponse> getCustomerBookings(Long customerId) {
        return bookingRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void sendNotification(Long bookingId) {
        Booking booking = findBookingById(bookingId);

        validationService.validateNotificationEligibility(booking);

        long minutesRemaining = validationService.calculateMinutesUntilPickup(
                new Date(),
                booking.getPickupTime()
        );

        notificationService.sendBookingReminder(booking, minutesRemaining);

        booking.setNotificationSent(true);
        bookingRepository.save(booking);
    }

    private Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", id));
    }

    private void validateTaxiAvailability(Taxi taxi) {
        if (!TaxiStatus.AVAILABLE.equals(taxi.getStatus())) {
            throw new BusinessException("Taxi is not available (current status: " + taxi.getStatus() + ")");
        }
    }

    private Booking buildBooking(Taxi taxi, Customer customer, Date pickupTime, double price) {
        Booking booking = new Booking();
        booking.setTaxi(taxi);
        booking.setCustomer(customer);
        booking.setPickupTime(pickupTime);
        booking.setPrice(price);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreated(new Date());
        booking.setNotificationSent(false);
        return booking;
    }

    private BookingResponse convertToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .taxiId(booking.getTaxi().getId())
                .taxiName(booking.getTaxi().getName())
                .customerId(booking.getCustomer().getId())
                .customerName(booking.getCustomer().getName())
                .pickupTime(booking.getPickupTime())
                .price(booking.getPrice())
                .status(booking.getStatus())
                .notificationSent(booking.getNotificationSent())
                .build();
    }
}