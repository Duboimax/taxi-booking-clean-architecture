package com.taxibooking.controllers;

import com.taxibooking.models.Taxi;
import com.taxibooking.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/taxis")
public class TaxiRestController {

    @Autowired
    private TaxiRepository taxiRepository;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableTaxis() {
        var taxis = taxiRepository.findByStatus("available");

        if (taxis.isEmpty()) {
            return ResponseEntity.ok("No taxis available");
        }

        return ResponseEntity.ok(taxis);
    }

    @PostMapping
    public ResponseEntity<?> addTaxi(@RequestBody Taxi t) {

        if (t.getName() == null || t.getName().equals("")) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        t.setStatus("available");
        t.setCreated(new Date());

        if (t.getPrice() == null) {
            t.setPrice(15.0);
        }

        Taxi saved = taxiRepository.save(t);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/release/{id}")
    public ResponseEntity<?> releaseTaxi(@PathVariable Long id) {
        var taxiOpt = taxiRepository.findById(id);

        if (!taxiOpt.isPresent()) {
            return ResponseEntity.badRequest().body("not found");
        }

        Taxi t = taxiOpt.get();

        t.setStatus("available");
        taxiRepository.save(t);

        return ResponseEntity.ok("Taxi released");
    }
}
