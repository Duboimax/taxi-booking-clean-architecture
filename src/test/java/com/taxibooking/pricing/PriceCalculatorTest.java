package com.taxibooking.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Price Calculator TDD Tests")
class PriceCalculatorTest {

    private PriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PriceCalculator();
    }


    @Test
    @DisplayName("Should calculate simple daytime price correctly")
    void shouldCalculateSimpleDaytimePrice() {
        LocalDateTime tuesdayAfternoon = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(10.0, 15, tuesdayAfternoon);

        double price = calculator.calculate(ride);

        assertEquals(24.50, price, 0.01);
    }

    @Test
    @DisplayName("Should calculate price with different distance and duration")
    void shouldCalculatePriceWithDifferentDistanceAndDuration() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 10, 0);
        Ride ride = new Ride(5.0, 20, daytime);

        double price = calculator.calculate(ride);

        assertEquals(18.50, price, 0.01);
    }


    @Test
    @DisplayName("Should reject negative distance")
    void shouldRejectNegativeDistance() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 10, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Ride(-5.0, 10, daytime),
                "Distance cannot be negative");
    }

    @Test
    @DisplayName("Should reject zero distance")
    void shouldRejectZeroDistance() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 10, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Ride(0.0, 10, daytime),
                "Distance must be greater than zero");
    }

    @Test
    @DisplayName("Should reject negative duration")
    void shouldRejectNegativeDuration() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 10, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Ride(10.0, -5, daytime),
                "Duration cannot be negative");
    }

    @Test
    @DisplayName("Should reject zero duration")
    void shouldRejectZeroDuration() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 10, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Ride(10.0, 0, daytime),
                "Duration must be greater than zero");
    }


    @Test
    @DisplayName("Should apply night surcharge at 11 PM")
    void shouldApplyNightSurchargeAt11PM() {
        LocalDateTime nightTime = LocalDateTime.of(2025, 1, 7, 23, 0);
        Ride ride = new Ride(10.0, 15, nightTime);

        double price = calculator.calculate(ride);

        assertEquals(34.25, price, 0.01);
    }

    @Test
    @DisplayName("Should apply night surcharge at midnight")
    void shouldApplyNightSurchargeAtMidnight() {
        LocalDateTime midnight = LocalDateTime.of(2025, 1, 7, 0, 0);
        Ride ride = new Ride(10.0, 15, midnight);

        double price = calculator.calculate(ride);

        assertEquals(34.25, price, 0.01);
    }

    @Test
    @DisplayName("Should apply night surcharge at 5 AM")
    void shouldApplyNightSurchargeAt5AM() {
        LocalDateTime earlyMorning = LocalDateTime.of(2025, 1, 7, 5, 0);
        Ride ride = new Ride(10.0, 15, earlyMorning);

        double price = calculator.calculate(ride);

        assertEquals(34.25, price, 0.01);
    }

    @Test
    @DisplayName("Should NOT apply night surcharge at 6 AM")
    void shouldNotApplyNightSurchargeAt6AM() {
        LocalDateTime morning = LocalDateTime.of(2025, 1, 7, 6, 0);
        Ride ride = new Ride(10.0, 15, morning);

        double price = calculator.calculate(ride);

        assertEquals(24.50, price, 0.01);
    }

    @Test
    @DisplayName("Should NOT apply night surcharge at 9 PM")
    void shouldNotApplyNightSurchargeAt9PM() {
        LocalDateTime evening = LocalDateTime.of(2025, 1, 7, 21, 0);
        Ride ride = new Ride(10.0, 15, evening);

        double price = calculator.calculate(ride);

        assertEquals(24.50, price, 0.01);
    }

    @Test
    @DisplayName("Should apply weekend surcharge on Saturday")
    void shouldApplyWeekendSurchargeOnSaturday() {
        LocalDateTime saturday = LocalDateTime.of(2025, 1, 11, 14, 0); // Saturday
        Ride ride = new Ride(10.0, 15, saturday);

        double price = calculator.calculate(ride);

        assertEquals(29.40, price, 0.01);
    }

    @Test
    @DisplayName("Should apply weekend surcharge on Sunday")
    void shouldApplyWeekendSurchargeOnSunday() {
        LocalDateTime sunday = LocalDateTime.of(2025, 1, 12, 14, 0); // Sunday
        Ride ride = new Ride(10.0, 15, sunday);

        double price = calculator.calculate(ride);

        assertEquals(29.40, price, 0.01);
    }

    @Test
    @DisplayName("Should NOT apply weekend surcharge on Monday")
    void shouldNotApplyWeekendSurchargeOnMonday() {
        LocalDateTime monday = LocalDateTime.of(2025, 1, 13, 14, 0); // Monday
        Ride ride = new Ride(10.0, 15, monday);

        double price = calculator.calculate(ride);

        assertEquals(24.50, price, 0.01);
    }

    @Test
    @DisplayName("Should apply both night and weekend surcharges on Saturday night")
    void shouldApplyNightAndWeekendSurcharges() {
        LocalDateTime saturdayNight = LocalDateTime.of(2025, 1, 11, 23, 0);
        Ride ride = new Ride(10.0, 15, saturdayNight);

        double price = calculator.calculate(ride);

        assertEquals(41.10, price, 0.01);
    }

    // ====================================================================
    // CYCLE 5: Airport supplement (Red-Green-Refactor)
    // ====================================================================

    @Test
    @DisplayName("Should add airport supplement for departure from airport")
    void shouldAddAirportSupplementForDeparture() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(10.0, 15, daytime, true, false);

        double price = calculator.calculate(ride);

        assertEquals(34.50, price, 0.01);
    }

    @Test
    @DisplayName("Should add airport supplement for arrival at airport")
    void shouldAddAirportSupplementForArrival() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(10.0, 15, daytime, false, true);

        double price = calculator.calculate(ride);

        assertEquals(34.50, price, 0.01);
    }

    @Test
    @DisplayName("Should NOT double airport supplement for round trip")
    void shouldNotDoubleAirportSupplementForRoundTrip() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(10.0, 15, daytime, true, true);

        double price = calculator.calculate(ride);

        assertEquals(34.50, price, 0.01);
    }

    @Test
    @DisplayName("Should combine airport and night surcharges")
    void shouldCombineAirportAndNightSurcharges() {
        LocalDateTime nightTime = LocalDateTime.of(2025, 1, 7, 23, 0);
        Ride ride = new Ride(10.0, 15, nightTime, true, false);

        // When
        double price = calculator.calculate(ride);

        assertEquals(44.25, price, 0.01);
    }


    @Test
    @DisplayName("Should apply minimum price for very short ride")
    void shouldApplyMinimumPrice() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(0.5, 1, daytime);

        double price = calculator.calculate(ride);

        assertEquals(8.00, price, 0.01);
    }

    @Test
    @DisplayName("Should NOT apply minimum price if price exceeds minimum")
    void shouldNotApplyMinimumPriceWhenExceeded() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(10.0, 15, daytime);

        double price = calculator.calculate(ride);

        assertEquals(24.50, price, 0.01);
    }

    @Test
    @DisplayName("Should apply minimum price even with airport supplement")
    void shouldApplyMinimumPriceWithAirport() {
        LocalDateTime daytime = LocalDateTime.of(2025, 1, 7, 14, 0);
        Ride ride = new Ride(0.5, 1, daytime, true, false);

        // When
        double price = calculator.calculate(ride);

        assertEquals(18.00, price, 0.01);
    }

    @Test
    @DisplayName("Should handle all surcharges combined (Saturday night from airport)")
    void shouldHandleAllSurchargesCombined() {
        LocalDateTime saturdayNight = LocalDateTime.of(2025, 1, 11, 23, 0);
        Ride ride = new Ride(10.0, 15, saturdayNight, true, false);

        double price = calculator.calculate(ride);

        assertEquals(51.10, price, 0.01);
    }

    @Test
    @DisplayName("Should handle realistic scenario - Monday morning commute")
    void shouldHandleRealisticMorningCommute() {
        LocalDateTime mondayMorning = LocalDateTime.of(2025, 1, 13, 8, 0);
        Ride ride = new Ride(8.0, 20, mondayMorning);

        double price = calculator.calculate(ride);

        assertEquals(23.00, price, 0.01);
    }
}