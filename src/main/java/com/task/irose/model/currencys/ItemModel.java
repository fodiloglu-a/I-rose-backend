package com.task.irose.model.currencys;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
@MappedSuperclass
public class ItemModel {

    @GeneratedValue(
            strategy = GenerationType.IDENTITY

    )
    @Id
    @Column(name = "model_id")
    private Long id;

    @Column(name = "model_datum")
    @Temporal(TemporalType.DATE)
    private Date datum;

    @Column(name = "model_sifra")
    private String sifra;

    @Column(name = "model_exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "model_oznaka")
    private String oznaka;

    public ItemModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public ItemModel(Long id, Date datum, String sifra, BigDecimal exchangeRate, String oznaka) {
        this.id = id;
        this.datum = datum;
        this.sifra = sifra;
        this.exchangeRate = exchangeRate;
        this.oznaka = oznaka;
    }
}
