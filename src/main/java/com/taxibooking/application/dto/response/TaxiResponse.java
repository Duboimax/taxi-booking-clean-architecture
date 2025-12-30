package com.taxibooking.application.dto.response;

import java.time.LocalDateTime;

public record TaxiResponse(Long id,
                           String name,
                           String plate,
                           Double price,
                           String status,
                           LocalDateTime createdAt)
{ }
