package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.usecases.booking.CompleteBookingUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class CompleteBookingController {

    private final CompleteBookingUseCase useCase;

    public CompleteBookingController(CompleteBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/complete")
    @Transactional
    public ResponseEntity<Void> execute(@PathVariable Long id) {
        useCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}