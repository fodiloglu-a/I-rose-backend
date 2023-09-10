package com.task.irose.service.Imp;


import com.task.irose.dao.CurrencyRepository;
import com.task.irose.exception.BusinessException;

import com.task.irose.model.CurrencyModel;
import com.task.irose.service.DataService;
import com.task.irose.service.pullData.PullDataService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    private final PullDataService pullDataService;

    private final CurrencyRepository currencyRepository;

    public DataServiceImp(PullDataService pullDataService, CurrencyRepository currencyRepository) {
        this.pullDataService = pullDataService;
        this.currencyRepository = currencyRepository;

    }


    @Override
    public String create() throws BusinessException {
        try {
            List<CurrencyModel> currencys = pullDataService.pullData();
            return "successful";
        }catch (BusinessException exception){
            throw new BusinessException("create method getexception", HttpStatus.NO_CONTENT);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }





}
