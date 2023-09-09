package com.task.irose.service.Imp;

import com.task.irose.dao.CurrencyRepository;
import com.task.irose.model.CurrencyModel;
import com.task.irose.service.GraphService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphServiceImp implements GraphService {
    /**
     2023-09-07
     */
    private LocalDate localDate=LocalDate.of(2023,9,07);


    private final CurrencyRepository currencyRepository;

    public GraphServiceImp(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    /**
     *
     * @param ozanaka
     * @return
     * @throws RuntimeException
     */

    @Override
    public HashMap<Integer, Double> weekCalculate(String ozanaka) throws RuntimeException {
        HashMap<Integer,Double> graphValue=new HashMap<>();
        try {

            /**
             * LocalDate::2023.09.07
              */

            graphValue=new HashMap<>();
            int day=0;
            List<CurrencyModel> modelForGraphQuery = currencyRepository.getModelForGraphQuery(ozanaka, localDate.minusWeeks(1));
            for (CurrencyModel currencyModel : modelForGraphQuery) {
                graphValue.put(day,currencyModel.getExchangeRate().doubleValue());day++;
            }

        }catch (RuntimeException e){

        }
        return graphValue;
    }

    /**
     * average tı weak
     *
     * @param ozanaka
     * @return
     * @throws RuntimeException
     */
    @Override
    public HashMap<Integer, Double> monthCalculate(String ozanaka) throws RuntimeException {
        HashMap<Integer,Double> graphValue=new HashMap<>();
        try {

            int count=0;
            LocalDate testDate=this.localDate.minusWeeks(1);
            LocalDate defDate=this.localDate;
            while (count<=3){//aylık ise işlem 4 defa yapılacak
                Map<String, Object> objectMap = currencyRepository.getTotalPriceAndModelCount(ozanaka,testDate,defDate);
                putHash(objectMap,count, graphValue);
                defDate=testDate;
                testDate=defDate.minusWeeks(1);
                count++;
            }


        }catch (RuntimeException exception){
            exception.getStackTrace();
        }
        return graphValue;
    }

    @Override
    public HashMap<Integer, Double> yearCalculate(String ozanaka) throws RuntimeException {
        HashMap<Integer,Double> graphValue=new HashMap<>();
        try {

            int count=0;
            LocalDate testDate=this.localDate.minusMonths(1);
            LocalDate defDate=this.localDate;
            while (count<=11){//aylık ise işlem 4 defa yapılacak
                Map<String, Object> objectMap = currencyRepository.getTotalPriceAndModelCount(ozanaka,testDate,defDate);
                putHash(objectMap,count, graphValue);
                defDate=testDate;
                testDate=defDate.minusMonths(1);
                count++;
            }


        }catch (RuntimeException exception){
            exception.getStackTrace();
        }
        return graphValue;
    }

    @Override
    public HashMap<Integer, Double> fiveYearCalculate(String ozanaka) throws RuntimeException {
        HashMap<Integer,Double> graphValue=new HashMap<>();
        try {

            int count=0;
            LocalDate testDate=this.localDate.minusYears(1);
            LocalDate defDate=this.localDate;
            while (count<=4){//aylık ise işlem 4 defa yapılacak
                Map<String, Object> objectMap = currencyRepository.getTotalPriceAndModelCount(ozanaka,testDate,defDate);
                putHash(objectMap,count,graphValue );
                defDate=testDate;
                testDate=defDate.minusYears(1);
                count++;
            }


        }catch (RuntimeException exception){
            exception.getStackTrace();
        }
        return graphValue;
    }

    @Override
    public HashMap<Integer, Double> allTimeCalculate(String ozanaka) throws RuntimeException {
        HashMap<Integer,Double> graphValue=new HashMap<>();
        try {

            int count=0;
            LocalDate testDate=this.localDate.minusYears(1);
            LocalDate defDate=this.localDate;
            while (count<=15){//aylık ise işlem 15 defa yapılacak
                Map<String, Object> objectMap = currencyRepository.getTotalPriceAndModelCount(ozanaka,testDate,defDate);
                putHash(objectMap,count,graphValue );
                defDate=testDate;
                testDate=defDate.minusYears(1);
                count++;
            }


        }catch (RuntimeException exception){
            exception.getStackTrace();
        }
        return graphValue;
    }


    public void  putHash(Map<String, Object> objectMap ,int count,HashMap<Integer,Double> graphValue){


        BigDecimal totalExchangeRate = (BigDecimal) objectMap.get("totalExchangeRate");
        Long modelCount = (Long) objectMap.get("modelCount");

        double result = 0.0; // Varsayılan değer, sıfıra bölme hatasını önlemek için

        if (modelCount != null && modelCount > 0) {
            result = totalExchangeRate.divide(BigDecimal.valueOf(modelCount), 2, RoundingMode.HALF_UP).doubleValue();
            // Yukarıdaki kod, sonucu iki ondalık basamağa yuvarlar (isteğe bağlı olarak ayarlayabilirsiniz).
            // HALF_UP yuvarlama modunu kullanır.
        }

        graphValue.put(count, result);
    }
}
