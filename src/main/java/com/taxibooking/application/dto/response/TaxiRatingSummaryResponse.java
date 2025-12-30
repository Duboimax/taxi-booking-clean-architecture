package com.taxibooking.application.dto.response;

import java.util.List;

public record TaxiRatingSummaryResponse(
        Long taxiId,
        String taxiName,
        Double averageRating,
        Integer totalRatings,
        List<RatingResponse> ratings
) {}