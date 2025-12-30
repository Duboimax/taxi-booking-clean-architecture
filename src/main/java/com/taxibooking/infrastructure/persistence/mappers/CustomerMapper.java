package com.taxibooking.infrastructure.persistence.mappers;

import com.taxibooking.domain.models.Customer;
import com.taxibooking.infrastructure.persistence.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity toEntity(Customer domain) {
        if (domain == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());

        return entity;
    }

    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;

        Customer domain = new Customer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setPhone(entity.getPhone());

        return domain;
    }
}