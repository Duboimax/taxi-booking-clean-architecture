package com.taxibooking.application.usecases.booking;

import com.taxibooking.application.dto.request.CreateBookingRequest;
import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.*;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;
import com.taxibooking.domain.services.PriceCalculator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;
    private final CustomerRepository customerRepository;
    private final PriceCalculator priceCalculator;

    public CreateBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository,
            PriceCalculator priceCalculator) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
        this.customerRepository = customerRepository;
        this.priceCalculator = priceCalculator;
    }

    public BookingResponse execute(CreateBookingRequest request) {
        Taxi taxi = taxiRepository.findById(request.taxiId())
                .orElseThrow(() -> new ResourceNotFoundException("Taxi", request.taxiId()));

        if (!taxi.isAvailable()) {
            throw new IllegalStateException("Taxi is not available (current status: " + taxi.getStatus() + ")");
        }

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", request.customerId()));

        LocalDateTime pickupTime = Instant.ofEpochMilli(request.pickupTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        Ride ride = new Ride(
                request.distanceKm(),
                request.durationMinutes(),
                pickupTime,
                Boolean.TRUE.equals(request.fromAirport()),
                Boolean.TRUE.equals(request.toAirport())
        );

        double calculatedPrice = priceCalculator.calculate(ride);

        Booking booking = new Booking();
        booking.setTaxiId(taxi.getId());
        booking.setCustomerId(customer.getId());
        booking.setPickupTime(pickupTime);
        booking.setPrice(calculatedPrice);
        booking.setDistanceKm(request.distanceKm());
        booking.setDurationMinutes(request.durationMinutes());
        booking.setFromAirport(request.fromAirport());
        booking.setToAirport(request.toAirport());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setNotificationSent(false);
        booking.setStatus(BookingStatus.CONFIRMED);

        taxi.book();
        taxiRepository.save(taxi);

        Booking savedBooking = bookingRepository.save(booking);

        return new BookingResponse(
                savedBooking.getId(),
                savedBooking.getTaxiId(),
                taxi.getName(),
                savedBooking.getCustomerId(),
                customer.getName(),
                savedBooking.getPickupTime(),
                savedBooking.getPrice(),
                savedBooking.getStatus().name(),
                savedBooking.getNotificationSent(),
                savedBooking.getCreatedAt()
        );
    }
}