package com.taxibooking.application.usecases.booking;

import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

import java.util.List;

public class GetCustomerBookingsUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;
    private final CustomerRepository customerRepository;

    public GetCustomerBookingsUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
        this.customerRepository = customerRepository;
    }

    public List<BookingResponse> execute(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));

        return bookingRepository.findByCustomerId(customerId).stream()
                .map(booking -> {
                    Taxi taxi = taxiRepository.findById(booking.getTaxiId()).orElse(null);
                    return new BookingResponse(
                            booking.getId(),
                            booking.getTaxiId(),
                            taxi != null ? taxi.getName() : null,
                            booking.getCustomerId(),
                            customer.getName(),
                            booking.getPickupTime(),
                            booking.getPrice(),
                            booking.getStatus().name(),
                            booking.getNotificationSent(),
                            booking.getCreatedAt()
                    );
                })
                .toList();
    }
}