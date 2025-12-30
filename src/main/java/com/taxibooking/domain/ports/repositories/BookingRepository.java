package com.taxibooking.domain.ports.repositories;

import com.taxibooking.domain.models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(Long id);

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByTaxiId(Long taxiId);

    List<Booking> findAll();
}