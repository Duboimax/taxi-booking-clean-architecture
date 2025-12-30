package com.taxibooking.domain.ports.repositories;

import com.taxibooking.domain.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();
}