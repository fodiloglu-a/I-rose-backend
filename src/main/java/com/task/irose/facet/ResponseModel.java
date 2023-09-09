package com.task.irose.facet;

import com.task.irose.facet.dto.CurrencyDTO;
import com.task.irose.model.CurrencyModel;

import java.util.*;

public class ResponseModel {
    private String oznakaFirs;
    private String oznakaSecont;
    private Date startDate;
    private Date endDate;
    private CurrencyDTO currencyDTO;
    private List<CurrencyDTO> currencyDTOs=new ArrayList<>();
    private HashMap<Integer,Double>  weekGraph=new HashMap<>();
    private HashMap<Integer,Double>  monthGraph=new HashMap<>();
    private HashMap<Integer,Double>  yearGraph=new HashMap<>();
    private HashMap<Integer,Double>  fiveYearGraph=new HashMap<>();
    private HashMap<Integer,Double>  allTimeGraph=new HashMap<>();


    public ResponseModel() {
    }

    public HashMap<Integer, Double> getWeekGraph() {
        return weekGraph;
    }

    public void setWeekGraph(HashMap<Integer, Double> weekGraph) {
        this.weekGraph = weekGraph;
    }

    public HashMap<Integer, Double> getMonthGraph() {
        return monthGraph;
    }

    public void setMonthGraph(HashMap<Integer, Double> monthGraph) {
        this.monthGraph = monthGraph;
    }

    public HashMap<Integer, Double> getYearGraph() {
        return yearGraph;
    }

    public void setYearGraph(HashMap<Integer, Double> yearGraph) {
        this.yearGraph = yearGraph;
    }

    public HashMap<Integer, Double> getFiveYearGraph() {
        return fiveYearGraph;
    }

    public void setFiveYearGraph(HashMap<Integer, Double> fiveYearGraph) {
        this.fiveYearGraph = fiveYearGraph;
    }

    public HashMap<Integer, Double> getAllTimeGraph() {
        return allTimeGraph;
    }

    public void setAllTimeGraph(HashMap<Integer, Double> allTimeGraph) {
        this.allTimeGraph = allTimeGraph;
    }

    public List<CurrencyDTO> getCurrencyDTOs() {
        return currencyDTOs;
    }

    public void setCurrencyDTOs(List<CurrencyDTO> currencyDTOs) {
        this.currencyDTOs = currencyDTOs;
    }

    public ResponseModel(String oznakaFirs, String oznakaSecont, Date startDate, Date endDate, CurrencyDTO currencyDTO) {
        this.oznakaFirs = oznakaFirs;
        this.oznakaSecont = oznakaSecont;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currencyDTO = currencyDTO;
    }

    public String getOznakaFirs() {
        return oznakaFirs;
    }

    public void setOznakaFirs(String oznakaFirs) {
        this.oznakaFirs = oznakaFirs;
    }

    public String getOznakaSecont() {
        return oznakaSecont;
    }

    public void setOznakaSecont(String oznakaSecont) {
        this.oznakaSecont = oznakaSecont;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CurrencyDTO getCurrencyDTO() {
        return currencyDTO;
    }

    public void setCurrencyDTO(CurrencyDTO currencyDTO) {
        this.currencyDTO = currencyDTO;
    }
}
