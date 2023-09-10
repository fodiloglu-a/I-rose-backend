package com.task.irose.service;



import com.task.irose.facet.ResponseModel;
import com.task.irose.facet.dto.CurrencyDTO;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyService {
    List<CurrencyDTO> getByDatum(LocalDate datum);
    List<CurrencyDTO> rateCalculateForDay(List<CurrencyDTO> list,LocalDate date);
    boolean rateCalculateForWeek(List<CurrencyDTO> list,LocalDate date);
    boolean rateCalculateForMonth(List<CurrencyDTO> list,LocalDate date);
    boolean rateCalculateForYear(List<CurrencyDTO> list,LocalDate date);
    boolean rateCalculateFor5Year(List<CurrencyDTO> list,LocalDate date);
    boolean rateCalculateForAllTime(List<CurrencyDTO> list,LocalDate date);

    ResponseModel getGraph(String oznaka);

    ResponseModel getCurrencyPage();
}
