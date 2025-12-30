package com.taxibooking.application.usecases.taxi;

import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

public class ReleaseTaxiUseCase {

    private final TaxiRepository taxiRepository;

    public ReleaseTaxiUseCase(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public void execute(Long taxiId) {
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new ResourceNotFoundException("Taxi", taxiId));

        taxi.release();
        taxiRepository.save(taxi);
    }
}