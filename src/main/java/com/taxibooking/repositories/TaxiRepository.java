package com.taxibooking.repositories;

import com.taxibooking.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {

    List<Taxi> findByStatus(String status);
}
