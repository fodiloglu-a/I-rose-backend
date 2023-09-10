package com.task.irose.service.Imp;

import com.task.irose.dao.CurrencyRepository;
import com.task.irose.exception.BusinessException;
import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.model.CurrencyModel;
import com.task.irose.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateServiceImp implements CalculateService {

    private final CurrencyRepository repository;

    public CalculateServiceImp(CurrencyRepository repository) {
        this.repository = repository;
    }

    /*
   in this method
   * can do betweean to two currencys(day/weak/month/year/5years/alltime) to compare
   * and do graph for models

   localDate.now=2023.09.07

    */
    @Override
    public ResponseModel compare(ResponseModel calculateRequestModel) {
        ResponseModel responseModel=new ResponseModel();
        try {
            String firstCurrencyOznaka=calculateRequestModel.getOznakaFirs();
            String secondCurrencyOznaka=calculateRequestModel.getOznakaSecont();
            LocalDate startDate=calculateRequestModel.getStartDate();
            LocalDate endDate=calculateRequestModel.getEndDate();
            //bana start date ile end date arasında  bu iki oznakaya ait tüm veriler lazım
            if (endDate.isAfter(LocalDate.of(2023,9,07))){
                endDate=LocalDate.of(2023,9,07);
            }
            List<CurrencyModel> firsCurrencies= repository.getCurrencyForCompare(startDate,endDate,firstCurrencyOznaka);
            List<CurrencyModel> secondCurrencies= repository.getCurrencyForCompare(startDate,endDate,secondCurrencyOznaka);

             profitAndLoseCalculate(firsCurrencies, secondCurrencies,responseModel);
        }catch (BusinessException exception){
            throw new BusinessException("compare mthod exceptions",HttpStatus.BAD_REQUEST);
        }

        return responseModel;
    }



    private void profitAndLoseCalculate(List<CurrencyModel> firsCurrencies, List<CurrencyModel> secondCurrencies,ResponseModel responseModel) {
        try {

            BigDecimal firstRate=secondCurrencies.get(0).getExchangeRate().divide(firsCurrencies.get(0).getExchangeRate(),4,RoundingMode.HALF_UP);

            responseModel.getProfitAndLose().put("firsRate",firstRate.doubleValue());

            BigDecimal lastRate=secondCurrencies.get(secondCurrencies.size()-1).getExchangeRate().divide(firsCurrencies.get(firsCurrencies.size()-1).getExchangeRate(),2,RoundingMode.HALF_UP);

            responseModel.getProfitAndLose().put("lastRate",lastRate.doubleValue());

            BigDecimal profitAndLose = lastRate.subtract(firstRate);

            responseModel.getProfitAndLose().put("profitAndLose",profitAndLose.doubleValue());




        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
