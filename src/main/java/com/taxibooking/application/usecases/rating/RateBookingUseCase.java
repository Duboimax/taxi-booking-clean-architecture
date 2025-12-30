package com.taxibooking.application.usecases.rating;

import com.taxibooking.application.dto.request.RateBookingRequest;
import com.taxibooking.application.dto.response.RatingResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

public class RateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;
    private final CustomerRepository customerRepository;

    public RateBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
        this.customerRepository = customerRepository;
    }

    public RatingResponse execute(Long bookingId, RateBookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId));

        booking.rate(request.stars(), request.comment());

        Booking savedBooking = bookingRepository.save(booking);

        Taxi taxi = taxiRepository.findById(savedBooking.getTaxiId()).orElse(null);
        Customer customer = customerRepository.findById(savedBooking.getCustomerId()).orElse(null);

        return new RatingResponse(
                savedBooking.getId(),
                savedBooking.getTaxiId(),
                taxi != null ? taxi.getName() : null,
                savedBooking.getCustomerId(),
                customer != null ? customer.getName() : null,
                savedBooking.getRating().getStars(),
                savedBooking.getRating().getComment(),
                savedBooking.getRating().getRatedAt()
        );
    }
}