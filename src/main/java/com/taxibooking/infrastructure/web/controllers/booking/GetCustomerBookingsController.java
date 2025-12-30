package com.taxibooking.infrastructure.web.controllers.booking;

import com.taxibooking.application.dto.response.BookingResponse;
import com.taxibooking.application.usecases.booking.GetCustomerBookingsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class GetCustomerBookingsController {

    private final GetCustomerBookingsUseCase useCase;

    public GetCustomerBookingsController(GetCustomerBookingsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingResponse>> execute(@PathVariable Long customerId) {
        var response = useCase.execute(customerId);
        return ResponseEntity.ok(response);
    }
}