package com.taxibooking.application.usecases.customer;

import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.domain.ports.repositories.CustomerRepository;

import java.util.List;

public class GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    public GetAllCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> execute() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone()
                ))
                .toList();
    }
}