package com.taxibooking.application.dto.request;

public record CreateCustomerRequest(
        String name,
        String email,
        String phone
) { }
