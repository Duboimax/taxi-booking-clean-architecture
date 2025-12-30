package com.taxibooking.application.dto.response;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long taxiId,
        String taxiName,
        Long customerId,
        String customerName,
        LocalDateTime pickupTime,
        Double price,
        String status,
        Boolean notificationSent,
        LocalDateTime createdAt
) {
}
