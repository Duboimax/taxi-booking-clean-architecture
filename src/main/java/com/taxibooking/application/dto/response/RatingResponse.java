package com.taxibooking.application.dto.response;

import java.time.LocalDateTime;

public record RatingResponse(
        Long bookingId,
        Long taxiId,
        String taxiName,
        Long customerId,
        String customerName,
        Integer stars,
        String comment,
        LocalDateTime ratedAt
) {}