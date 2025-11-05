package com.taxibooking.controllers;

import com.taxibooking.models.Customer;
import com.taxibooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Customer c) {
        if (c.getName() == null) {
            return ResponseEntity.badRequest().body("no name");
        }

        if (c.getEmail() != null) {
            var existing = customerRepository.findByEmail(c.getEmail());
            if (existing.isPresent()) {
                return ResponseEntity.badRequest().body("email already exists");
            }
        }

        Customer saved = customerRepository.save(c);
        return ResponseEntity.ok(saved);
    }


}
