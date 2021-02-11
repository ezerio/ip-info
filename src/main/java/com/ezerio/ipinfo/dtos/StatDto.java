package com.ezerio.ipinfo.dtos;

import java.math.BigInteger;

public class StatDto {

    private String countryCode;
    private Integer distance;
    private Long count;

    public StatDto() {
    }

    public StatDto(Object[] object) {
        this.countryCode = (String) object[0];
        this.distance = ((Integer) object[1]);
        this.count = ((BigInteger) object[2]).longValue();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
