package com.taxibooking.application.dto.request;

public record CreateBookingRequest(
        Long taxiId,
        Long customerId,
        Long pickupTime,
        Double distanceKm,
        Integer durationMinutes,
        Boolean fromAirport,
        Boolean toAirport
) {
    public CreateBookingRequest(Long taxiId, Long customerId, Long pickupTime,
                                Double distanceKm, Integer durationMinutes) {
        this(taxiId, customerId, pickupTime, distanceKm, durationMinutes, false, false);
    }
}
