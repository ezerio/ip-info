package com.ezerio.ipinfo.entitys;

import javax.persistence.*;

@Entity
@Table(name = "FOREX")
public class ForeignExchange {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "RATE")
    private Float rate;

    @Column(name = "LAST_UPDATE")
    private Long timestamp;

    public ForeignExchange() {
    }

    public ForeignExchange(String currency, Float rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
