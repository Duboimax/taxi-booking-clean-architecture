package com.taxibooking.domain.exceptions;

public class TaxiNotAvailableException extends BusinessException {

    private final Long taxiId;
    private final String currentStatus;

    public TaxiNotAvailableException(Long taxiId, String currentStatus) {
        super("Taxi " + taxiId + " is not available (current status: " + currentStatus + ")");
        this.taxiId = taxiId;
        this.currentStatus = currentStatus;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }
}