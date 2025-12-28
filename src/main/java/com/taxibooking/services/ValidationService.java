package com.taxibooking.services;

import com.taxibooking.exceptions.BusinessException;
import com.taxibooking.models.Booking;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.taxibooking.config.PricingConstants.NOTIFICATION_THRESHOLD_MINUTES;

@Service
public class ValidationService {

    public void validateNotificationEligibility(Booking booking) {
        Date now = new Date();
        Date pickupTime = booking.getPickupTime();

        long minutesUntilPickup = calculateMinutesUntilPickup(now, pickupTime);

        if (minutesUntilPickup > NOTIFICATION_THRESHOLD_MINUTES) {
            throw new BusinessException(
                    String.format("Too early to notify. Pickup is in %d minutes (threshold: %d minutes)",
                            minutesUntilPickup, NOTIFICATION_THRESHOLD_MINUTES)
            );
        }

        if (minutesUntilPickup < 0) {
            throw new BusinessException("Cannot notify: pickup time has already passed");
        }
    }

    public long calculateMinutesUntilPickup(Date from, Date to) {
        long diffInMillis = to.getTime() - from.getTime();
        return diffInMillis / (60 * 1000);
    }

    public void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new BusinessException(fieldName + " is required");
        }
    }

    public void validatePrice(Double price) {
        if (price != null && price < 0) {
            throw new BusinessException("Price cannot be negative");
        }
    }
}