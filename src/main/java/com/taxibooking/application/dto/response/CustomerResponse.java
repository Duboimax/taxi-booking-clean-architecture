package com.taxibooking.application.dto.response;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone
) { }
