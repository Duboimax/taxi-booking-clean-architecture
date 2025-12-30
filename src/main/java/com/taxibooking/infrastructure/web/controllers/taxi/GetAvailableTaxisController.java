package com.taxibooking.infrastructure.web.controllers.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.application.usecases.taxi.GetAvailableTaxisUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxis")
public class GetAvailableTaxisController {

    private final GetAvailableTaxisUseCase useCase;

    public GetAvailableTaxisController(GetAvailableTaxisUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/available")
    public ResponseEntity<List<TaxiResponse>> execute() {
        var response = useCase.execute();
        return ResponseEntity.ok(response);
    }
}