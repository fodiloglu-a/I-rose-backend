package com.task.irose.service.Imp;


import com.task.irose.dao.CurrencyRepository;
import com.task.irose.exception.BusinessException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.facet.dto.CurrencyDTO;
import com.task.irose.facet.dto.CurrencyPopulator;
import com.task.irose.model.CurrencyModel;
import com.task.irose.service.CurrencyService;
import com.task.irose.service.GraphService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyPopulator populator;
    private final CurrencyRepository repository;
    private final GraphService graphService;

    public CurrencyServiceImp(CurrencyPopulator populator, CurrencyRepository repository, GraphService graphService) {
        this.populator = populator;
        this.repository = repository;
        this.graphService = graphService;
    }

    @Override
    public  List<CurrencyDTO>  getByDatum(LocalDate datum) {
        try {

            List<CurrencyDTO>  currencyDTO =new ArrayList<>();

            List<CurrencyModel> models =  repository.findCurrencyModelByDatum(datum);

            for (CurrencyModel currencyModel : models) {
                currencyDTO.add(populator.modelToDto(currencyModel));
            }
            rateCalculateForDay(currencyDTO,datum);
            rateCalculateForWeek(currencyDTO,datum);
            rateCalculateForMonth(currencyDTO,datum);
            rateCalculateForYear(currencyDTO,datum);
            rateCalculateFor5Year(currencyDTO,datum);
            rateCalculateForAllTime(currencyDTO,datum);
            return currencyDTO;

        } catch (BusinessException e) {
            throw new BusinessException("getAllCurrency exception to service", HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public List<CurrencyDTO> rateCalculateForDay(List<CurrencyDTO> firsModels, LocalDate date) {
        try {
            LocalDate lastDate=date.minusDays(1);
            List<CurrencyModel> lastModels=repository.findCurrencyModelByDatum(lastDate);
            for (CurrencyModel lastModel : lastModels) {
                for (CurrencyDTO firstModel : firsModels) {

                    if (firstModel.getSifra().equals(lastModel.getSifra())){
                        firstModel.setRateDay(percentageChange(firstModel.getExchangeRate(),lastModel.getExchangeRate()));
                        break;
                    }
                }
            }

        }catch (RuntimeException e){

        }
        return firsModels;

    }


    @Override
    public boolean rateCalculateForWeek(List<CurrencyDTO> list, LocalDate date) {
        try {
            List<CurrencyModel> lastList=   repository.findCurrencyModelByDatum(date.minusWeeks(1));
            for (CurrencyModel currencyModel : lastList) {
                for (CurrencyDTO dto:list) {

                    if (dto.getSifra().equals(currencyModel.getSifra())){
                        dto.setRateWeek(percentageChange(dto.getExchangeRate(),currencyModel.getExchangeRate()));break;
                    }
                }

            }
            return true;

        }catch (RuntimeException e){

        }
        return false;
    }

    @Override
    public boolean rateCalculateForMonth(List<CurrencyDTO> list, LocalDate date) {
        try {
            List<CurrencyModel> lastYearList=   repository.findCurrencyModelByDatum(date.minusMonths(1));
            for (CurrencyModel currencyModel : lastYearList) {
                for (CurrencyDTO dto:list) {

                    if (dto.getSifra().equals(currencyModel.getSifra())){
                        dto.setRateMonth(percentageChange(dto.getExchangeRate(),currencyModel.getExchangeRate()));break;
                    }
                }

            }
            return true;

        }catch (RuntimeException e){

        }
        return false;

    }

    @Override
    public boolean rateCalculateForYear(List<CurrencyDTO> list, LocalDate date) {
        try {
            List<CurrencyModel> lastYearList=   repository.findCurrencyModelByDatum(date.minusYears(1));
            for (CurrencyModel currencyModel : lastYearList) {
                for (CurrencyDTO dto:list) {

                    if (dto.getSifra().equals(currencyModel.getSifra())){
                        dto.setRateYear(percentageChange(dto.getExchangeRate(),currencyModel.getExchangeRate()));break;
                    }
                }

            }
            return true;

        }catch (RuntimeException e){

        }
        return false;
    }

    @Override
    public boolean rateCalculateFor5Year(List<CurrencyDTO> list, LocalDate date) {
        try {
            List<CurrencyModel> lastYearList=   repository.findCurrencyModelByDatum(date.minusYears(5));
            for (CurrencyModel currencyModel : lastYearList) {
                for (CurrencyDTO dto:list) {

                    if (dto.getSifra().equals(currencyModel.getSifra())){
                        dto.setRateFiveYear(percentageChange(dto.getExchangeRate(),currencyModel.getExchangeRate()));break;
                    }
                }

            }
            return true;

        }catch (RuntimeException e){

        }
        return false;
    }


    @Override
    public boolean rateCalculateForAllTime(List<CurrencyDTO> list, LocalDate date) {
        try {
            /**
             * firs commit to db date=2007.01.01
             */

            LocalDate ss=LocalDate.of(2007,1,03);
            List<CurrencyModel> lastYearList=   repository.findCurrencyModelByDatum(ss);
            for (CurrencyModel currencyModel : lastYearList) {
                for (CurrencyDTO dto:list) {
                    if (dto.getSifra().equals(currencyModel.getSifra())){
                        dto.setRateAllTime(percentageChange(dto.getExchangeRate(),currencyModel.getExchangeRate()));break;
                    }
                }

            }
            return true;

        }catch (RuntimeException e){

        }
        return false;

    }

    @Override
    public ResponseModel getGraph(String oznaka) {
        ResponseModel responseModel=new ResponseModel();
        try {
            responseModel.setWeekGraph(graphService.weekCalculate(oznaka));
            responseModel.setMonthGraph(graphService.monthCalculate(oznaka));
            responseModel.setYearGraph(graphService.yearCalculate(oznaka));

            responseModel.setFiveYearGraph(graphService.fiveYearCalculate(oznaka));
            responseModel.setAllTimeGraph(graphService.allTimeCalculate(oznaka));

        }catch (BusinessException businessException){

        }
        return responseModel;
    }

    @Override
    public ResponseModel getCurrencyPage() {
        ResponseModel responseModel=new ResponseModel();
        try {

            LocalDate date=LocalDate.of(2023,9,01);
            List<CurrencyModel> currencyModelByDatum = repository.getCurrencyByOznakaUniq(date);
             currencyModelByDatum.forEach( model -> {
                 responseModel.getCurrencyDTOs().add(populator.modelToDto(model));
                     });
             return responseModel;

        }catch (BusinessException businessException){

        }
        return responseModel;
    }


    public double percentageChange(BigDecimal firstExchangeRate,BigDecimal lastExchangeRate){
        BigDecimal percentageChange =new BigDecimal(0);
        try {

             percentageChange = ((lastExchangeRate.subtract(firstExchangeRate))
                    .divide(firstExchangeRate, 4, BigDecimal.ROUND_HALF_UP))
                    .multiply(new BigDecimal(100));

        }catch (RuntimeException exception){

        }
        return percentageChange.doubleValue();

    }


}
