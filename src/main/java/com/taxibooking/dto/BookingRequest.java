package com.taxibooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    @NotNull(message = "Taxi ID is required")
    private Long taxiId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Pickup time is required")
    private Long pickupTime; // Timestamp in milliseconds
}
