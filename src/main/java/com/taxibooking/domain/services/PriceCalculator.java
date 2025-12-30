package com.taxibooking.domain.services;

import com.taxibooking.domain.models.Ride;

public class PriceCalculator {

    private static final double BASE_FARE = 5.0;
    private static final double PRICE_PER_KM = 1.50;
    private static final double PRICE_PER_MINUTE = 0.30;

    private static final double NIGHT_SURCHARGE_MULTIPLIER = 1.5;
    private static final double WEEKEND_SURCHARGE_MULTIPLIER = 1.2;

    private static final double AIRPORT_SUPPLEMENT = 10.0;
    private static final double MINIMUM_PRICE = 8.0;

    public double calculate(Ride ride) {
        double price = calculateBaseFare();
        price += calculateDistanceCost(ride);
        price += calculateDurationCost(ride);

        if (ride.isWeekend()) {
            price = applyWeekendSurcharge(price);
        }

        price = applyMinimumPrice(price);

        if (ride.involvesAirport()) {
            price += AIRPORT_SUPPLEMENT;
        }

        return roundToTwoDecimals(price);
    }

    private double calculateBaseFare() {
        return BASE_FARE;
    }

    private double calculateDistanceCost(Ride ride) {
        double cost = ride.getDistanceKm() * PRICE_PER_KM;

        if (ride.isNightTime()) {
            cost *= NIGHT_SURCHARGE_MULTIPLIER;
        }

        return cost;
    }

    private double calculateDurationCost(Ride ride) {
        double cost = ride.getDurationMinutes() * PRICE_PER_MINUTE;

        if (ride.isNightTime()) {
            cost *= NIGHT_SURCHARGE_MULTIPLIER;
        }

        return cost;
    }

    private double applyWeekendSurcharge(double price) {
        return price * WEEKEND_SURCHARGE_MULTIPLIER;
    }

    private double applyMinimumPrice(double price) {
        return Math.max(price, MINIMUM_PRICE);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}