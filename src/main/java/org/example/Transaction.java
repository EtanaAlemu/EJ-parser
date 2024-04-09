package org.example;

import java.util.ArrayList;
import java.util.List;

// Transaction class to represent a transaction with multiple events
class Transaction {
    private boolean isCardless;
    private boolean isDispensed;
    private String transactionStart;
    private String branchCode;
    private String authorizationReference;
    private String unknownData;
    private String PAN;
    private String withdrawalAmount;
    private String currencyCode;
    private String surcharge;
    private String chargedFee;
    private String dispenseEvent;
    private String dispensedAmount;
    private List<Event> events = new ArrayList<>();

    public boolean isCardless() {
        return isCardless;
    }

    public void setCardless(boolean cardless) {
        isCardless = cardless;
    }

    public boolean isDispensed() {
        return isDispensed;
    }

    public void setDispensed(boolean dispensed) {
        isDispensed = dispensed;
    }

    // Method to add an event to the transaction
    public void addEvent(Event event) {
        events.add(event);
    }

    public String getTransactionStart() {
        return transactionStart;
    }

    public void setTransactionStart(String transactionStart) {
        this.transactionStart = transactionStart;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAuthorizationReference() {
        return authorizationReference;
    }

    public void setAuthorizationReference(String authorizationReference) {
        this.authorizationReference = authorizationReference;
    }

    public String getUnknownData() {
        return unknownData;
    }

    public void setUnknownData(String unknownData) {
        this.unknownData = unknownData;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getChargedFee() {
        return chargedFee;
    }

    public void setChargedFee(String chargedFee) {
        this.chargedFee = chargedFee;
    }

    public String getDispenseEvent() {
        return dispenseEvent;
    }

    public void setDispenseEvent(String dispenseEvent) {
        this.dispenseEvent = dispenseEvent;
    }

    public String getDispensedAmount() {
        return dispensedAmount;
    }

    public void setDispensedAmount(String dispensedAmount) {
        this.dispensedAmount = dispensedAmount;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
