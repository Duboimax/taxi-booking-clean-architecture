package com.taxibooking.infrastructure.config;

import com.taxibooking.application.usecases.booking.*;
import com.taxibooking.application.usecases.customer.*;
import com.taxibooking.application.usecases.rating.*;
import com.taxibooking.application.usecases.taxi.*;
import com.taxibooking.domain.ports.repositories.BookingRepository;
import com.taxibooking.domain.ports.repositories.CustomerRepository;
import com.taxibooking.domain.ports.repositories.TaxiRepository;
import com.taxibooking.domain.services.PriceCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public PriceCalculator priceCalculator() {
        return new PriceCalculator();
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository,
            PriceCalculator priceCalculator) {
        return new CreateBookingUseCase(bookingRepository, taxiRepository, customerRepository, priceCalculator);
    }

    @Bean
    public GetBookingUseCase getBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        return new GetBookingUseCase(bookingRepository, taxiRepository, customerRepository);
    }

    @Bean
    public GetCustomerBookingsUseCase getCustomerBookingsUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        return new GetCustomerBookingsUseCase(bookingRepository, taxiRepository, customerRepository);
    }

    @Bean
    public CancelBookingUseCase cancelBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository) {
        return new CancelBookingUseCase(bookingRepository, taxiRepository);
    }

    @Bean
    public CompleteBookingUseCase completeBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository) {
        return new CompleteBookingUseCase(bookingRepository, taxiRepository);
    }

    @Bean
    public SendBookingNotificationUseCase sendBookingNotificationUseCase(
            BookingRepository bookingRepository) {
        return new SendBookingNotificationUseCase(bookingRepository);
    }

    @Bean
    public CreateTaxiUseCase createTaxiUseCase(TaxiRepository taxiRepository) {
        return new CreateTaxiUseCase(taxiRepository);
    }

    @Bean
    public GetTaxiUseCase getTaxiUseCase(TaxiRepository taxiRepository) {
        return new GetTaxiUseCase(taxiRepository);
    }

    @Bean
    public GetAllTaxisUseCase getAllTaxisUseCase(TaxiRepository taxiRepository) {
        return new GetAllTaxisUseCase(taxiRepository);
    }

    @Bean
    public GetAvailableTaxisUseCase getAvailableTaxisUseCase(TaxiRepository taxiRepository) {
        return new GetAvailableTaxisUseCase(taxiRepository);
    }

    @Bean
    public ReleaseTaxiUseCase releaseTaxiUseCase(TaxiRepository taxiRepository) {
        return new ReleaseTaxiUseCase(taxiRepository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepository customerRepository) {
        return new CreateCustomerUseCase(customerRepository);
    }

    @Bean
    public GetCustomerUseCase getCustomerUseCase(CustomerRepository customerRepository) {
        return new GetCustomerUseCase(customerRepository);
    }

    @Bean
    public GetAllCustomersUseCase getAllCustomersUseCase(CustomerRepository customerRepository) {
        return new GetAllCustomersUseCase(customerRepository);
    }

    @Bean
    public RateBookingUseCase rateBookingUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        return new RateBookingUseCase(bookingRepository, taxiRepository, customerRepository);
    }

    @Bean
    public GetTaxiRatingsUseCase getTaxiRatingsUseCase(
            BookingRepository bookingRepository,
            TaxiRepository taxiRepository,
            CustomerRepository customerRepository) {
        return new GetTaxiRatingsUseCase(bookingRepository, taxiRepository, customerRepository);
    }
}