package com.taxibooking.domain.ports.repositories;

import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.models.TaxiStatus;

import java.util.List;
import java.util.Optional;

public interface TaxiRepository {

    Taxi save(Taxi taxi);

    Optional<Taxi> findById(Long id);

    List<Taxi> findByStatus(TaxiStatus status);

    List<Taxi> findAvailable();

    List<Taxi> findAll();
}