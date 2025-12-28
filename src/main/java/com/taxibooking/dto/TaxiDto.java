package com.taxibooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxiDto {
    private Long id;
    private String name;
    private String plate;
    private Double pricePerKm;
    private String status;
}
