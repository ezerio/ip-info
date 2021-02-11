package com.ezerio.ipinfo.apis.dto;

import java.util.List;

public class CountryInfoDto {

    private String country;
    private String isoCode;
    private List<String> languages;
    private List<CurrencyDto> currency;
    private List<String> times;
    private String estimateDistance;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<CurrencyDto> getCurrency() {
        return currency;
    }

    public void setCurrency(List<CurrencyDto> currency) {
        this.currency = currency;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public String getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(String estimateDistance) {
        this.estimateDistance = estimateDistance;
    }
}
