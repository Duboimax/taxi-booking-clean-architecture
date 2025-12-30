package com.taxibooking.infrastructure.persistence.repositories;

import com.taxibooking.infrastructure.persistence.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByCustomerId(Long customerId);

    List<BookingEntity> findByTaxiId(Long taxiId);
}