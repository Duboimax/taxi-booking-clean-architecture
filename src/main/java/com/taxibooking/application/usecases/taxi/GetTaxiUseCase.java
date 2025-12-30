package com.taxibooking.application.usecases.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

public class GetTaxiUseCase {

    private final TaxiRepository taxiRepository;

    public GetTaxiUseCase(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public TaxiResponse execute(Long taxiId) {
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new ResourceNotFoundException("Taxi", taxiId));

        return new TaxiResponse(
                taxi.getId(),
                taxi.getName(),
                taxi.getPlate(),
                taxi.getPrice(),
                taxi.getStatus().name(),
                taxi.getCreatedAt()
        );
    }
}