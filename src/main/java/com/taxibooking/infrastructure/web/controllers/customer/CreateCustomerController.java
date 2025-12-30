package com.taxibooking.infrastructure.web.controllers.customer;

import com.taxibooking.application.dto.request.CreateCustomerRequest;
import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.application.usecases.customer.CreateCustomerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CreateCustomerController {

    private final CreateCustomerUseCase useCase;

    public CreateCustomerController(CreateCustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerResponse> execute(@RequestBody CreateCustomerRequest request) {
        var response = useCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}