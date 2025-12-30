package com.taxibooking.application.dto.request;

public record RateBookingRequest(
        Integer stars,
        String comment
) {}