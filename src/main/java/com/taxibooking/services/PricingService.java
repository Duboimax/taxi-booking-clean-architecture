package com.taxibooking.services;

import com.taxibooking.pricing.PriceCalculator;
import com.taxibooking.pricing.Ride;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final PriceCalculator priceCalculator;

    public double calculatePrice(double distanceKm, int durationMinutes, Date pickupTime,
                                 boolean fromAirport, boolean toAirport) {
        LocalDateTime pickupDateTime = convertToLocalDateTime(pickupTime);

        Ride ride = new Ride(distanceKm, durationMinutes, pickupDateTime, fromAirport, toAirport);

        return priceCalculator.calculate(ride);
    }

    @Deprecated
    public double calculatePrice(double basePrice, Date pickupTime) {
        double estimatedDistance = Math.max(1.0, basePrice / 1.5);
        int estimatedDuration = (int) Math.max(5, estimatedDistance * 2); // Rough estimate

        return calculatePrice(estimatedDistance, estimatedDuration, pickupTime, false, false);
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}