package com.ezerio.ipinfo.apis.dto;

public class CurrencyDto {

    private String code;
    private String name;
    private String symbol;

    public CurrencyDto() {
    }

    public CurrencyDto(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}