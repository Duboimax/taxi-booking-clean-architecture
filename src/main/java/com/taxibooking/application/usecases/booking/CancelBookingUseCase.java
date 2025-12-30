package com.taxibooking.application.usecases.booking;

import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

public class CancelBookingUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;

    public CancelBookingUseCase(BookingRepository bookingRepository, TaxiRepository taxiRepository) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
    }

    public void execute(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId));

        booking.cancel();

        taxiRepository.findById(booking.getTaxiId()).ifPresent(taxi -> {
            taxi.release();
            taxiRepository.save(taxi);
        });

        bookingRepository.save(booking);
    }
}