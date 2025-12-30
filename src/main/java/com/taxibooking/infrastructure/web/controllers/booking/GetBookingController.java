package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.application.usecases.booking.GetBookingUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class GetBookingController {

    private final GetBookingUseCase useCase;

    public GetBookingController(GetBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> execute(@PathVariable Long id) {
        var response = useCase.execute(id);
        return ResponseEntity.ok(response);
    }
}