package com.taxibooking.domain.models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Ride {

    private final double distanceKm;
    private final int durationMinutes;
    private final LocalDateTime pickupTime;
    private final boolean startsAtAirport;
    private final boolean endsAtAirport;

    public Ride(double distanceKm, int durationMinutes, LocalDateTime pickupTime) {
        this(distanceKm, durationMinutes, pickupTime, false, false);
    }

    public Ride(double distanceKm, int durationMinutes, LocalDateTime pickupTime,
                boolean startsAtAirport, boolean endsAtAirport) {
        validateDistance(distanceKm);
        validateDuration(durationMinutes);
        Objects.requireNonNull(pickupTime, "Pickup time cannot be null");

        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
        this.pickupTime = pickupTime;
        this.startsAtAirport = startsAtAirport;
        this.endsAtAirport = endsAtAirport;
    }

    private void validateDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero");
        }
    }

    private void validateDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
    }

    public boolean involvesAirport() {
        return startsAtAirport || endsAtAirport;
    }

    public boolean isNightTime() {
        int hour = pickupTime.getHour();
        return hour >= 22 || hour < 6;
    }

    public boolean isWeekend() {
        DayOfWeek day = pickupTime.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public boolean isStartsAtAirport() {
        return startsAtAirport;
    }

    public boolean isEndsAtAirport() {
        return endsAtAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Double.compare(ride.distanceKm, distanceKm) == 0 &&
                durationMinutes == ride.durationMinutes &&
                startsAtAirport == ride.startsAtAirport &&
                endsAtAirport == ride.endsAtAirport &&
                Objects.equals(pickupTime, ride.pickupTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distanceKm, durationMinutes, pickupTime, startsAtAirport, endsAtAirport);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "distanceKm=" + distanceKm +
                ", durationMinutes=" + durationMinutes +
                ", pickupTime=" + pickupTime +
                ", airport=" + involvesAirport() +
                '}';
    }
}