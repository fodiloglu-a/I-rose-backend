package com.task.irose.facet.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CurrencyDTO {


    private LocalDate datum;

    private String sifra;


    private BigDecimal exchangeRate;


    private String oznaka;

    private double rateDay;
    private double rateWeek;
    private double rateMonth;
    private double rateYear;
    private double rateFiveYear;
    private double rateAllTime;


    public double getRateDay() {
        return rateDay;
    }

    public void setRateDay(double rateDay) {
        this.rateDay = rateDay;
    }

    public double getRateWeek() {
        return rateWeek;
    }

    public void setRateWeek(double rateWeek) {
        this.rateWeek = rateWeek;
    }

    public double getRateMonth() {
        return rateMonth;
    }

    public void setRateMonth(double rateMonth) {
        this.rateMonth = rateMonth;
    }

    public double getRateYear() {
        return rateYear;
    }

    public void setRateYear(double rateYear) {
        this.rateYear = rateYear;
    }

    public double getRateFiveYear() {
        return rateFiveYear;
    }

    public void setRateFiveYear(double rateFiveYear) {
        this.rateFiveYear = rateFiveYear;
    }

    public double getRateAllTime() {
        return rateAllTime;
    }

    public void setRateAllTime(double rateAllTime) {
        this.rateAllTime = rateAllTime;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
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
}
