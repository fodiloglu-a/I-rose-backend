package com.task.irose.service.Imp;

import com.task.irose.dao.CurrencyRepository;
import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.model.CurrencyModel;
import com.task.irose.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculateServiceImp implements CalculateService {

    private final CurrencyRepository repository;

    public CalculateServiceImp(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public double compare(ResponseModel calculateRequestModel) {
        try {
            List<CurrencyModel> firstModels=repository.getCurrencyModelByOznakaAndDatum(
                    calculateRequestModel.getStartDate(),
                    calculateRequestModel.getEndDate(),
                    calculateRequestModel.getOznakaFirs()

            );
            List<CurrencyModel> secondModels=repository.getCurrencyModelByOznakaAndDatum(
                    calculateRequestModel.getStartDate(),
                    calculateRequestModel.getEndDate(),
                    calculateRequestModel.getOznakaSecont()
            );
            /*
            calculate for percentage
             */
            double percentage = calculateToCompare(firstModels, secondModels, calculateRequestModel);


            return percentage;
        }catch (ResponseException exception){
            throw new ResponseException("to compare exception service", HttpStatus.BAD_GATEWAY);
        }

    }

    /**
     * fist model to date filter
     * firstStart firstEnd
     */
    /**
     * second model to date filter
     * secondStart secondEnd
     */
    /**
     * Calculate
     * fistRate=firstStart divide secondStart
     * secondRate=firstEnd divide secondEnd
     */
    /**
     * percentage ratio calculation
     */

    private double calculateToCompare(List<CurrencyModel> firstModels, List<CurrencyModel> secondModels,ResponseModel calculateRequestModel) {
        try {

            CurrencyModel firsStart=firstModels.stream().filter(
                    currency  -> currency.getDatum().equals(calculateRequestModel.getStartDate())
            ).findFirst().get();
            CurrencyModel firstEnd=firstModels.stream().filter(
                    currency  -> currency.getDatum().equals(calculateRequestModel.getEndDate())
            ).findFirst().get();



            CurrencyModel secondStart=firstModels.stream().filter(
                    currency  -> currency.getDatum().equals(calculateRequestModel.getStartDate())
            ).findFirst().get();
            CurrencyModel secondEnd=firstModels.stream().filter(
                    currency  -> currency.getDatum().equals(calculateRequestModel.getEndDate())
            ).findFirst().get();


            BigDecimal firsDivide=firsStart.getExchangeRate().divide(secondStart.getExchangeRate());
            BigDecimal  secondDivide=firstEnd.getExchangeRate().divide(secondEnd.getExchangeRate());



            BigDecimal percent=new BigDecimal(100);
            BigDecimal percentage=((secondDivide.subtract(firsDivide)).divide(percent)).multiply(percent);
            percentage = percentage.setScale(2, RoundingMode.HALF_UP);

            return percentage.doubleValue();




        }catch (ResponseException exception){
            throw new ResponseException("to compare exception service", HttpStatus.BAD_GATEWAY);
        }
    }




}
