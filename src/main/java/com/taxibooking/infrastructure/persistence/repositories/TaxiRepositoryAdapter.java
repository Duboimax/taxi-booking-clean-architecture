package com.taxibooking.infrastructure.persistence.repositories;

import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.models.TaxiStatus;
import com.taxibooking.domain.ports.repositories.TaxiRepository;
import com.taxibooking.infrastructure.persistence.mappers.TaxiMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaxiRepositoryAdapter implements TaxiRepository {

    private final JpaTaxiRepository jpaRepository;
    private final TaxiMapper mapper;

    public TaxiRepositoryAdapter(JpaTaxiRepository jpaRepository, TaxiMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Taxi save(Taxi taxi) {
        var entity = mapper.toEntity(taxi);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Taxi> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Taxi> findByStatus(TaxiStatus status) {
        return jpaRepository.findByStatus(status.name()).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Taxi> findAvailable() {
        return findByStatus(TaxiStatus.AVAILABLE);
    }

    @Override
    public List<Taxi> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}