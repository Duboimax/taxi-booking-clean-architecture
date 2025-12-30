package com.taxibooking.infrastructure.web.controllers.rating;

import com.taxibooking.application.dto.response.TaxiRatingSummaryResponse;
import com.taxibooking.application.usecases.rating.GetTaxiRatingsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxis")
public class GetTaxiRatingsController {

    private final GetTaxiRatingsUseCase useCase;

    public GetTaxiRatingsController(GetTaxiRatingsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<TaxiRatingSummaryResponse> execute(@PathVariable Long id) {
        var response = useCase.execute(id);
        return ResponseEntity.ok(response);
    }
}