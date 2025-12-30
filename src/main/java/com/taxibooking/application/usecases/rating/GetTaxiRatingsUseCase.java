package com.taxibooking.application.usecases.rating;

import com.taxibooking.application.dto.response.RatingResponse;
import com.taxibooking.application.dto.response.TaxiRatingSummaryResponse;
import com.taxibooking.domain.exceptions.ResourceNotFoundException;
import com.taxibooking.domain.models.Booking;
import com.taxibooking.domain.models.Customer;
import com.taxibooking.domain.models.Taxi;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;

import java.util.List;

public class GetTaxiRatingsUseCase {

    private final BookingRepository bookingRepository;
    private final TaxiRepository taxiRepository;
    private final CustomerRepository customerRepository;

    public GetTaxiRatingsUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.taxiRepository = taxiRepository;
        this.customerRepository = customerRepository;
    }

    public TaxiRatingSummaryResponse execute(Long taxiId) {
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new ResourceNotFoundException("Taxi", taxiId));

        List<Booking> ratedBookings = bookingRepository.findByTaxiId(taxiId).stream()
                .filter(Booking::isRated)
                .toList();

        List<RatingResponse> ratings = ratedBookings.stream()
                .map(booking -> {
                    Customer customer = customerRepository.findById(booking.getCustomerId()).orElse(null);
                    return new RatingResponse(
                            booking.getId(),
                            booking.getTaxiId(),
                            taxi.getName(),
                            booking.getCustomerId(),
                            customer != null ? customer.getName() : null,
                            booking.getRating().getStars(),
                            booking.getRating().getComment(),
                            booking.getRating().getRatedAt()
                    );
                })
                .toList();

        double averageRating = ratings.isEmpty() ? 0.0 :
                ratings.stream()
                        .mapToInt(RatingResponse::stars)
                        .average()
                        .orElse(0.0);

        averageRating = Math.round(averageRating * 10.0) / 10.0;

        return new TaxiRatingSummaryResponse(
                taxi.getId(),
                taxi.getName(),
                averageRating,
                ratings.size(),
                ratings
        );
    }
}