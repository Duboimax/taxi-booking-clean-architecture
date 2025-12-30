package com.taxibooking.infrastructure.web.controllers.customer;

import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.application.usecases.customer.GetCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class GetCustomerController {

    private final GetCustomerUseCase useCase;

    public GetCustomerController(GetCustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> execute(@PathVariable Long id) {
        var response = useCase.execute(id);
        return ResponseEntity.ok(response);
    }
}