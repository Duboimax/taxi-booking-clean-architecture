package com.taxibooking.infrastructure.persistence.repositories;

import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.infrastructure.persistence.mappers.BookingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepositoryAdapter implements BookingRepository {

    private final JpaBookingRepository jpaRepository;
    private final BookingMapper mapper;

    public BookingRepositoryAdapter(JpaBookingRepository jpaRepository, BookingMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Booking save(Booking booking) {
        var entity = mapper.toEntity(booking);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Booking> findByCustomerId(Long customerId) {
        return jpaRepository.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findByTaxiId(Long taxiId) {
        return jpaRepository.findByTaxiId(taxiId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}