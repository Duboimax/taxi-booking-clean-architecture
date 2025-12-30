package com.taxibooking.infrastructure.persistence.mappers;

import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.models.TaxiStatus;
import com.taxibooking.infrastructure.persistence.entities.TaxiEntity;
import org.springframework.stereotype.Component;

@Component
public class TaxiMapper {

    public TaxiEntity toEntity(Taxi domain) {
        if (domain == null) return null;

        TaxiEntity entity = new TaxiEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setPlate(domain.getPlate());
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus() != null ? domain.getStatus().name() : null);
        entity.setCreated(domain.getCreatedAt());

        return entity;
    }

    public Taxi toDomain(TaxiEntity entity) {
        if (entity == null) return null;

        Taxi domain = new Taxi();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setPlate(entity.getPlate());
        domain.setPrice(entity.getPrice());
        domain.setStatus(parseStatus(entity.getStatus()));
        domain.setCreatedAt(entity.getCreated());

        return domain;
    }

    private TaxiStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return TaxiStatus.AVAILABLE;
        }
        try {
            return TaxiStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return TaxiStatus.AVAILABLE;
        }
    }
}