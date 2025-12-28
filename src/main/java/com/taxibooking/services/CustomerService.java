package com.taxibooking.services;

import com.taxibooking.dto.CustomerDto;
import com.taxibooking.exceptions.BusinessException;
import com.taxibooking.exceptions.ResourceNotFoundException;
import com.taxibooking.models.Customer;
import com.taxibooking.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationService validationService;

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        validationService.validateName(customerDto.getName(), "Customer name");

        if (customerDto.getEmail() != null) {
            customerRepository.findByEmail(customerDto.getEmail())
                    .ifPresent(existingCustomer -> {
                        throw new BusinessException("Email already exists: " + customerDto.getEmail());
                    });
        }

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());

        Customer saved = customerRepository.save(customer);
        return convertToDto(saved);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    private CustomerDto convertToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}