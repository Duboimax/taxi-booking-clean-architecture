package com.taxibooking.domain.models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
    private Long id;
    private Long taxiId;
    private Long customerId;
    private LocalDateTime pickupTime;
    private Double price;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private Boolean notificationSent;

    private Double distanceKm;
    private Integer durationMinutes;
    private Boolean fromAirport;
    private Boolean toAirport;

    public Booking() {
        this.createdAt = LocalDateTime.now();
        this.notificationSent = false;
        this.status = BookingStatus.PENDING;
    }

    public Booking(Long id, Long taxiId, Long customerId, LocalDateTime pickupTime,
                   Double price, BookingStatus status, LocalDateTime createdAt,
                   Boolean notificationSent) {
        this.id = id;
        this.taxiId = taxiId;
        this.customerId = customerId;
        this.pickupTime = pickupTime;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.notificationSent = notificationSent;
    }

    public void confirm() {
        if (this.status != BookingStatus.PENDING) {
            throw new IllegalStateException("Cannot confirm a booking that is not pending");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        if (this.status == BookingStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed booking");
        }
        this.status = BookingStatus.CANCELLED;
    }

    public void complete() {
        if (this.status != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Cannot complete a booking that is not confirmed");
        }
        this.status = BookingStatus.COMPLETED;
    }

    public boolean canSendNotification() {
        return !Boolean.TRUE.equals(this.notificationSent)
                && this.status == BookingStatus.CONFIRMED
                && this.pickupTime != null
                && this.pickupTime.isAfter(LocalDateTime.now());
    }

    public void markNotificationSent() {
        if (!canSendNotification()) {
            throw new IllegalStateException("Cannot send notification for this booking");
        }
        this.notificationSent = true;
    }

    public long minutesUntilPickup() {
        if (pickupTime == null) {
            return 0;
        }
        return java.time.Duration.between(LocalDateTime.now(), pickupTime).toMinutes();
    }

    public boolean isNightTime() {
        if (pickupTime == null) return false;
        int hour = pickupTime.getHour();
        return hour >= 22 || hour < 6;
    }

    public boolean isWeekend() {
        if (pickupTime == null) return false;
        DayOfWeek day = pickupTime.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public boolean involvesAirport() {
        return Boolean.TRUE.equals(fromAirport) || Boolean.TRUE.equals(toAirport);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(Boolean notificationSent) {
        this.notificationSent = notificationSent;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Boolean getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Boolean fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Boolean getToAirport() {
        return toAirport;
    }

    public void setToAirport(Boolean toAirport) {
        this.toAirport = toAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", taxiId=" + taxiId +
                ", customerId=" + customerId +
                ", pickupTime=" + pickupTime +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}