package com.taxibooking.controllers;

import com.taxibooking.dto.BookingRequest;
import com.taxibooking.dto.BookingResponse;
import com.taxibooking.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;

    @PostMapping("/reserve")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse booking = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResponse>> getCustomerBookings(@PathVariable Long customerId) {
        List<BookingResponse> bookings = bookingService.getCustomerBookings(customerId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/notify/{bookingId}")
    public ResponseEntity<Void> sendNotification(@PathVariable Long bookingId) {
        bookingService.sendNotification(bookingId);
        return ResponseEntity.ok().build();
    }
}