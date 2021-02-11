package com.ezerio.ipinfo.entitys;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCY")
public class Currency {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNTRY_INFO_ID")
    private Long countryInfoId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "SYMBOL")
    private String symbol;

    public Currency() {
    }

    public Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryInfoId() {
        return countryInfoId;
    }

    public void setCountryInfoId(Long countryInfoId) {
        this.countryInfoId = countryInfoId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
