package com.taxibooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    private Long taxiId;
    private String taxiName;
    private Long customerId;
    private String customerName;
    private Date pickupTime;
    private Double price;
    private String status;
    private Boolean notificationSent;
}
