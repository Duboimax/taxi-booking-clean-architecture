package com.taxibooking.services;

import com.taxibooking.config.TaxiStatus;
import com.taxibooking.dto.TaxiDto;
import com.taxibooking.exceptions.ResourceNotFoundException;
import com.taxibooking.models.Taxi;
import com.taxibooking.repositories.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.taxibooking.config.PricingConstants.DEFAULT_PRICE_PER_KM;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaxiService {

    private final TaxiRepository taxiRepository;
    private final ValidationService validationService;

    public List<TaxiDto> getAvailableTaxis() {
        return taxiRepository.findByStatus(TaxiStatus.AVAILABLE)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaxiDto createTaxi(TaxiDto taxiDto) {
        validationService.validateName(taxiDto.getName(), "Taxi name");
        validationService.validatePrice(taxiDto.getPricePerKm());

        Taxi taxi = new Taxi();
        taxi.setName(taxiDto.getName());
        taxi.setPlate(taxiDto.getPlate());
        taxi.setPrice(taxiDto.getPricePerKm() != null ? taxiDto.getPricePerKm() : DEFAULT_PRICE_PER_KM);
        taxi.setStatus(TaxiStatus.AVAILABLE);
        taxi.setCreated(new Date());

        Taxi saved = taxiRepository.save(taxi);
        return convertToDto(saved);
    }

    @Transactional
    public void releaseTaxi(Long taxiId) {
        Taxi taxi = findTaxiById(taxiId);
        taxi.setStatus(TaxiStatus.AVAILABLE);
        taxiRepository.save(taxi);
    }

    public Taxi findTaxiById(Long id) {
        return taxiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxi", id));
    }

    private TaxiDto convertToDto(Taxi taxi) {
        return TaxiDto.builder()
                .id(taxi.getId())
                .name(taxi.getName())
                .plate(taxi.getPlate())
                .pricePerKm(taxi.getPrice())
                .status(taxi.getStatus())
                .build();
    }
}