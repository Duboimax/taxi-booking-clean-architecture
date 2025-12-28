package com.taxibooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookingRequest {

    @NotNull(message = "Taxi ID is required")
    private Long taxiId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Pickup time is required")
    private Long pickupTime; // Timestamp in milliseconds

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be positive")
    private Double distanceKm;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer durationMinutes;

    private Boolean fromAirport = false;
    private Boolean toAirport = false;
}