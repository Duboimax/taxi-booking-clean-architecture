package com.taxibooking.infrastructure.persistence.mappers;

import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.models.BookingStatus;
import com.taxibooking.domain.models.Rating;
import com.taxibooking.infrastructure.persistence.entities.BookingEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingEntity toEntity(Booking domain) {
        if (domain == null) return null;

        BookingEntity entity = new BookingEntity();
        entity.setId(domain.getId());
        entity.setTaxiId(domain.getTaxiId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setPickupTime(domain.getPickupTime());
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus() != null ? domain.getStatus().name() : null);
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setNotificationSent(domain.getNotificationSent());
        entity.setDistanceKm(domain.getDistanceKm());
        entity.setDurationMinutes(domain.getDurationMinutes());
        entity.setFromAirport(domain.getFromAirport());
        entity.setToAirport(domain.getToAirport());

        if (domain.getRating() != null) {
            entity.setRatingStars(domain.getRating().getStars());
            entity.setRatingComment(domain.getRating().getComment());
            entity.setRatedAt(domain.getRating().getRatedAt());
        }

        return entity;
    }

    public Booking toDomain(BookingEntity entity) {
        if (entity == null) return null;

        Booking domain = new Booking();
        domain.setId(entity.getId());
        domain.setTaxiId(entity.getTaxiId());
        domain.setCustomerId(entity.getCustomerId());
        domain.setPickupTime(entity.getPickupTime());
        domain.setPrice(entity.getPrice());
        domain.setStatus(parseStatus(entity.getStatus()));
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setNotificationSent(entity.getNotificationSent());
        domain.setDistanceKm(entity.getDistanceKm());
        domain.setDurationMinutes(entity.getDurationMinutes());
        domain.setFromAirport(entity.getFromAirport());
        domain.setToAirport(entity.getToAirport());

        if (entity.getRatingStars() != null) {
            Rating rating = new Rating(
                    entity.getRatingStars(),
                    entity.getRatingComment(),
                    entity.getRatedAt()
            );
            domain.setRating(rating);
        }

        return domain;
    }

    private BookingStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return BookingStatus.PENDING;
        }
        try {
            return BookingStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return BookingStatus.PENDING;
        }
    }
}