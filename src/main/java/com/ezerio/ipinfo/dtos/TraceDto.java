package com.ezerio.ipinfo.dtos;

import java.util.List;

public class TraceDto {
    private String ip;
    private String date;
    private String country;
    private String isoCode;
    private List<String> language;
    private List<String> currency;
    private List<String> times;
    private String estimateDistance;

    public static  Builder builder() {
        return new Builder();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> languages) {
        this.language = languages;
    }

    public List<String> getCurrency() {
        return currency;
    }

    public void setCurrency(List<String> currency) {
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

    public static class Builder {
        private TraceDto traceDto;

        private Builder() {
            this.traceDto = new TraceDto();
        }

        public TraceDto build() {
            return this.traceDto;
        }

        public Builder ip(String ip) {
            this.traceDto.ip = ip;
            return  this;
        }

        public Builder date(String date) {
            this.traceDto.date = date;
            return  this;
        }

        public Builder country(String country) {
            this.traceDto.country = country;
            return  this;
        }

        public Builder isoCode(String isoCode) {
            this.traceDto.isoCode = isoCode;
            return  this;
        }

        public Builder language(List<String> language) {
            this.traceDto.language = language;
            return  this;
        }

        public Builder currency(List<String> currency) {
            this.traceDto.currency = currency;
            return  this;
        }

        public Builder times(List<String> times) {
            this.traceDto.times = times;
            return  this;
        }

        public Builder estimateDistance(String estimateDistance) {
            this.traceDto.estimateDistance = estimateDistance;
            return  this;
        }

    }
}

