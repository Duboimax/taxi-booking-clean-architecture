package com.taxibooking.infrastructure.web.controllers.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.application.usecases.taxi.GetAllTaxisUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxis")
public class GetAllTaxisController {

    private final GetAllTaxisUseCase useCase;

    public GetAllTaxisController(GetAllTaxisUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<TaxiResponse>> execute() {
        var response = useCase.execute();
        return ResponseEntity.ok(response);
    }
}