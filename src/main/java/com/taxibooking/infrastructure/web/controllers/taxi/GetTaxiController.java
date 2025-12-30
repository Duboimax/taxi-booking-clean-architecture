package com.taxibooking.infrastructure.web.controllers.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.application.usecases.taxi.GetTaxiUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxis")
public class GetTaxiController {

    private final GetTaxiUseCase useCase;

    public GetTaxiController(GetTaxiUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxiResponse> execute(@PathVariable Long id) {
        var response = useCase.execute(id);
        return ResponseEntity.ok(response);
    }
}