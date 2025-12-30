package com.taxibooking.domain.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Taxi {

    private Long id;
    private String name;
    private String plate;
    private Double price;
    private TaxiStatus status;
    private LocalDateTime createdAt;

    public Taxi() {
        this.status = TaxiStatus.AVAILABLE;
        this.createdAt = LocalDateTime.now();
    }

    public Taxi(Long id, String name, String plate, Double price, TaxiStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plate = plate;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    public boolean isAvailable() {
        return this.status == TaxiStatus.AVAILABLE;
    }

    public void book() {
        if (!isAvailable()) {
            throw new IllegalStateException("Taxi is not available (current status: " + status + ")");
        }
        this.status = TaxiStatus.BOOKED;
    }

    public void release() {
        this.status = TaxiStatus.AVAILABLE;
    }

    public void setOutOfService() {
        this.status = TaxiStatus.OUT_OF_SERVICE;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TaxiStatus getStatus() {
        return status;
    }

    public void setStatus(TaxiStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(id, taxi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plate='" + plate + '\'' +
                ", status=" + status +
                '}';
    }
}