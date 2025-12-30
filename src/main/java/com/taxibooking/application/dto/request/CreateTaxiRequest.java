package com.taxibooking.application.dto.request;

public record CreateTaxiRequest(
        String name,
        String plate,
        Double price
) { }
