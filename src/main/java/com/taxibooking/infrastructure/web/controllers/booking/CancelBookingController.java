package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.usecases.booking.CancelBookingUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class CancelBookingController {

    private final CancelBookingUseCase useCase;

    public CancelBookingController(CancelBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/cancel")
    @Transactional
    public ResponseEntity<Void> execute(@PathVariable Long id) {
        useCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}