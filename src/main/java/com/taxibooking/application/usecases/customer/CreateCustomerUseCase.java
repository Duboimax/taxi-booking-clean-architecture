package com.taxibooking.application.usecases.customer;

import com.taxibooking.application.dto.request.CreateCustomerRequest;
import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.ports.repositories.CustomerRepository;

public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse execute(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail(),
                savedCustomer.getPhone()
        );
    }
}