package com.taxibooking.infrastructure.web.controllers.taxi;

import com.taxibooking.application.usecases.taxi.ReleaseTaxiUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taxis")
public class ReleaseTaxiController {

    private final ReleaseTaxiUseCase useCase;

    public ReleaseTaxiController(ReleaseTaxiUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{id}/release")
    @Transactional
    public ResponseEntity<Void> execute(@PathVariable Long id) {
        useCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}