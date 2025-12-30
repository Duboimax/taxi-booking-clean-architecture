package com.taxibooking.infrastructure.web.controllers.taxi;

import com.taxibooking.application.dto.request.CreateTaxiRequest;
import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.application.usecases.taxi.CreateTaxiUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxis")
public class CreateTaxiController {

    private final CreateTaxiUseCase useCase;

    public CreateTaxiController(CreateTaxiUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TaxiResponse> execute(@RequestBody CreateTaxiRequest request) {
        var response = useCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}