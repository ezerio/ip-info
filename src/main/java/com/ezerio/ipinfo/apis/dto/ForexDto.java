package com.ezerio.ipinfo.apis.dto;

public class ForexDto {

    private String currency;
    private Float rate;
    private Long timestamp;

    public ForexDto() {
    }

    public ForexDto(String currency, Float rate, Long timestamp) {
        this.currency = currency;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
