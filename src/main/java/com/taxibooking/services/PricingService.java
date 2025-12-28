package com.taxibooking.services;

import org.springframework.stereotype.Service;

import java.util.Date;

import static com.taxibooking.config.PricingConstants.*;

@Service
public class PricingService {

    public double calculatePrice(double basePrice, Date pickupTime) {
        if (isNightTime(pickupTime)) {
            return applyNightSurcharge(basePrice);
        }
        return basePrice;
    }

    private boolean isNightTime(Date pickupTime) {
        @SuppressWarnings("deprecation")
        int hour = pickupTime.getHours();
        return hour >= NIGHT_START_HOUR || hour < NIGHT_END_HOUR;
    }

    private double applyNightSurcharge(double basePrice) {
        return basePrice * NIGHT_SURCHARGE_MULTIPLIER;
    }
}