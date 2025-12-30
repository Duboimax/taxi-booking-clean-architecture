package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.usecases.booking.SendBookingNotificationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class SendBookingNotificationController {

    private final SendBookingNotificationUseCase useCase;

    public SendBookingNotificationController(SendBookingNotificationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/notify")
    @Transactional
    public ResponseEntity<Void> execute(@PathVariable Long id) {
        useCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}