package com.taxibooking.application.usecases.taxi;

import com.taxibooking.application.dto.request.CreateTaxiRequest;
import com.taxibooking.application.dto.response.TaxiResponse;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.models.TaxiStatus;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

import java.time.LocalDateTime;

public class CreateTaxiUseCase {

    private final TaxiRepository taxiRepository;

    public CreateTaxiUseCase(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public TaxiResponse execute(CreateTaxiRequest request) {
        Taxi taxi = new Taxi();
        taxi.setName(request.name());
        taxi.setPlate(request.plate());
        taxi.setPrice(request.price());
        taxi.setStatus(TaxiStatus.AVAILABLE);
        taxi.setCreatedAt(LocalDateTime.now());

        Taxi savedTaxi = taxiRepository.save(taxi);

        return new TaxiResponse(
                savedTaxi.getId(),
                savedTaxi.getName(),
                savedTaxi.getPlate(),
                savedTaxi.getPrice(),
                savedTaxi.getStatus().name(),
                savedTaxi.getCreatedAt()
        );
    }
}