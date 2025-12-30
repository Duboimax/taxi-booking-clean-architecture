package com.taxibooking.infrastructure.persistence.repositories;

import com.taxibooking.infrastructure.persistence.entities.TaxiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTaxiRepository extends JpaRepository<TaxiEntity, Long> {

    List<TaxiEntity> findByStatus(String status);
}