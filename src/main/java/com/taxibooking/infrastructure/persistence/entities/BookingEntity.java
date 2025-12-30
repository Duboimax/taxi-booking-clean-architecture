package com.taxibooking.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "taxi_id", nullable = false)
    private Long taxiId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "pickup_time")
    private LocalDateTime pickupTime;

    private Double price;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "notification_sent")
    private Boolean notificationSent;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "from_airport")
    private Boolean fromAirport;

    @Column(name = "to_airport")
    private Boolean toAirport;

    @Column(name = "rating_stars")
    private Integer ratingStars;

    @Column(name = "rating_comment", length = 500)
    private String ratingComment;

    @Column(name = "rated_at")
    private LocalDateTime ratedAt;

    public BookingEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTaxiId() { return taxiId; }
    public void setTaxiId(Long taxiId) { this.taxiId = taxiId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public LocalDateTime getPickupTime() { return pickupTime; }
    public void setPickupTime(LocalDateTime pickupTime) { this.pickupTime = pickupTime; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Boolean getNotificationSent() { return notificationSent; }
    public void setNotificationSent(Boolean notificationSent) { this.notificationSent = notificationSent; }

    public Double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(Double distanceKm) { this.distanceKm = distanceKm; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Boolean getFromAirport() { return fromAirport; }
    public void setFromAirport(Boolean fromAirport) { this.fromAirport = fromAirport; }

    public Boolean getToAirport() { return toAirport; }
    public void setToAirport(Boolean toAirport) { this.toAirport = toAirport; }

    public Integer getRatingStars() { return ratingStars; }
    public void setRatingStars(Integer ratingStars) { this.ratingStars = ratingStars; }

    public String getRatingComment() { return ratingComment; }
    public void setRatingComment(String ratingComment) { this.ratingComment = ratingComment; }

    public LocalDateTime getRatedAt() { return ratedAt; }
    public void setRatedAt(LocalDateTime ratedAt) { this.ratedAt = ratedAt; }
}