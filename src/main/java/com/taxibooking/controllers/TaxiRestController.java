package com.taxibooking.controllers;

import com.taxibooking.dto.TaxiDto;
import com.taxibooking.services.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxis")
@RequiredArgsConstructor
public class TaxiRestController {

    private final TaxiService taxiService;

    @GetMapping("/available")
    public ResponseEntity<List<TaxiDto>> getAvailableTaxis() {
        List<TaxiDto> availableTaxis = taxiService.getAvailableTaxis();
        return ResponseEntity.ok(availableTaxis);
    }

    @PostMapping
    public ResponseEntity<TaxiDto> createTaxi(@RequestBody TaxiDto taxiDto) {
        TaxiDto created = taxiService.createTaxi(taxiDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/release/{id}")
    public ResponseEntity<Void> releaseTaxi(@PathVariable Long id) {
        taxiService.releaseTaxi(id);
        return ResponseEntity.ok().build();
    }
}