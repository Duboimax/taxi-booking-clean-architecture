package com.taxibooking.application.usecases.booking;

import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

public class GetBookingUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;
    private final CustomerRepository customerRepository;

    public GetBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
        this.customerRepository = customerRepository;
    }

    public BookingResponse execute(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId));

        Taxi taxi = taxiRepository.findById(booking.getTaxiId()).orElse(null);
        Customer customer = customerRepository.findById(booking.getCustomerId()).orElse(null);

        return new BookingResponse(
                booking.getId(),
                booking.getTaxiId(),
                taxi != null ? taxi.getName() : null,
                booking.getCustomerId(),
                customer != null ? customer.getName() : null,
                booking.getPickupTime(),
                booking.getPrice(),
                booking.getStatus().name(),
                booking.getNotificationSent(),
                booking.getCreatedAt()
        );
    }
}