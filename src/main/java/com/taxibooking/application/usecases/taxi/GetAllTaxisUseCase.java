package com.taxibooking.application.usecases.taxi;

import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

import java.util.List;

public class GetAllTaxisUseCase {

    private final TaxiRepository taxiRepository;

    public GetAllTaxisUseCase(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public List<TaxiResponse> execute() {
        return taxiRepository.findAll().stream()
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