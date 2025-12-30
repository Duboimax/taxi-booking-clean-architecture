package com.taxibooking.application.usecases.customer;

import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.ports.repositories.CustomerRepository;

public class GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse execute(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}