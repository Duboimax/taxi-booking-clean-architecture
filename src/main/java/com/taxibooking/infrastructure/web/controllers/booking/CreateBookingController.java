package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.dto.request.CreateBookingRequest;
import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.application.usecases.booking.CreateBookingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class CreateBookingController {

    private final CreateBookingUseCase useCase;

    public CreateBookingController(CreateBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BookingResponse> execute(@RequestBody CreateBookingRequest request) {
        var response = useCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}