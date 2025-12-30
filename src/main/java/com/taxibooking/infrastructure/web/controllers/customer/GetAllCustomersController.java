package com.taxibooking.infrastructure.web.controllers.customer;

import com.taxibooking.application.dto.response.CustomerResponse;
import com.taxibooking.application.usecases.customer.GetAllCustomersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class GetAllCustomersController {

    private final GetAllCustomersUseCase useCase;

    public GetAllCustomersController(GetAllCustomersUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> execute() {
        var response = useCase.execute();
        return ResponseEntity.ok(response);
    }
}