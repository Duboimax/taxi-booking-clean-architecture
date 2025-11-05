package com.taxibooking.controllers;

import com.taxibooking.models.Booking;
import com.taxibooking.models.Taxi;
import com.taxibooking.repositories.BookingRepository;
import com.taxibooking.repositories.CustomerRepository;
import com.taxibooking.repositories.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("api/booking")
public class BookingRestController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestBody Map<String, Object> data) {
        Long taxiId = Long.parseLong(data.get("taxiId").toString());
        Long customerId = Long.parseLong(data.get("customerId").toString());
        Long pickupTime = Long.parseLong(data.get("pickupTime").toString()); // timestamp

        var taxiOpt = taxiRepository.findById(taxiId);
        if (!taxiOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Taxi not found");
        }

        Taxi t = taxiOpt.get();

        if (!t.getStatus().equals("available")) {
            return ResponseEntity.badRequest().body("Taxi not available");
        }

        var customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

        Double price = t.getPrice();
        Date pickup = new Date(pickupTime);

        int hour = pickup.getHours();
        if (hour >= 20 || hour < 6) {
            price = price * 1.5;
        }

        Booking b = new Booking();
        b.setTaxi(t);
        b.setCustomer(customerOpt.get());
        b.setPickupTime(pickup);
        b.setPrice(price);
        b.setStatus("confirmed");
        b.setCreated(new Date());
        b.setNotificationSent(false);

        t.setStatus("booked");
        taxiRepository.save(t);

        Booking saved = bookingRepository.save(b);

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerBookings(@PathVariable Long customerId) {
        var bookings = bookingRepository.findByCustomerId(customerId);

        if (bookings.isEmpty()) {
            return ResponseEntity.ok("No bookings found");
        }

        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/notify/{bookingId}")
    public ResponseEntity<?> sendNotification(@PathVariable Long bookingId) {
        var bookingOpt = bookingRepository.findById(bookingId);

        if (!bookingOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Booking not found");
        }

        Booking b = bookingOpt.get();

        Date now = new Date();
        Date pickup = b.getPickupTime();

        long diff = pickup.getTime() - now.getTime();
        long minutes = diff / (60 * 1000);

        if (minutes > 10) {
            return ResponseEntity.badRequest().body("Too early to notify (more than 10min)");
        }

        if (minutes < 0) {
            return ResponseEntity.badRequest().body("Pickup time already passed");
        }

        String msg = "Reminder: Your taxi " + b.getTaxi().getName() +
                " will arrive in " + minutes + " minutes!";
        System.out.println("=== NOTIFICATION SENT ===");
        System.out.println("To: " + b.getCustomer().getEmail());
        System.out.println("Message: " + msg);
        System.out.println("========================");

        b.setNotificationSent(true);
        bookingRepository.save(b);

        return ResponseEntity.ok("Notification sent");
    }
}
