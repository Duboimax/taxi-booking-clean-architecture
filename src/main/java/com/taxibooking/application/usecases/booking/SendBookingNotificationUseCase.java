package com.taxibooking.application.usecases.booking;

import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.ports.repositories.BookingRepository;

public class SendBookingNotificationUseCase {

    private final BookingRepository bookingRepository;

    public SendBookingNotificationUseCase(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void execute(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId));

        if (!booking.canSendNotification()) {
            throw new IllegalStateException("Cannot send notification for this booking");
        }

        booking.markNotificationSent();
        bookingRepository.save(booking);
    }
}