package com.taxibooking.infrastructure.web.controllers.rating;

import com.taxibooking.application.dto.request.RateBookingRequest;
import com.taxibooking.application.dto.response.RatingResponse;
import com.taxibooking.application.usecases.rating.RateBookingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class RateBookingController {

    private final RateBookingUseCase useCase;

    public RateBookingController(RateBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/rate")
    @Transactional
    public ResponseEntity<RatingResponse> execute(
            @PathVariable Long id,
            @RequestBody RateBookingRequest request) {
        var response = useCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}