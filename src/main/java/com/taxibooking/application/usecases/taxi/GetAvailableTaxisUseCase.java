package com.taxibooking.application.usecases.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

import java.util.List;

public class GetAvailableTaxisUseCase {

    private final TaxiRepository taxiRepository;

    public GetAvailableTaxisUseCase(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public List<TaxiResponse> execute() {
        return taxiRepository.findAvailable().stream()
                .map(taxi -> new TaxiResponse(
                        taxi.getId(),
                        taxi.getName(),
                        taxi.getPlate(),
                        taxi.getPrice(),
                        taxi.getStatus().name(),
                        taxi.getCreatedAt()
                ))
                .toList();
    }
}