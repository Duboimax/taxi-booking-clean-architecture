package com.taxibooking.services;

import com.taxibooking.models.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void sendBookingReminder(Booking booking, long minutesRemaining) {
        String message = buildReminderMessage(booking, minutesRemaining);
        sendNotification(booking.getCustomer().getEmail(), message);
    }

    private String buildReminderMessage(Booking booking, long minutesRemaining) {
        return String.format(
                "Reminder: Your taxi %s will arrive in %d minutes!",
                booking.getTaxi().getName(),
                minutesRemaining
        );
    }

    private void sendNotification(String recipient, String message) {
        log.info("=== NOTIFICATION SENT ===");
        log.info("To: {}", recipient);
        log.info("Message: {}", message);
        log.info("========================");
    }
}