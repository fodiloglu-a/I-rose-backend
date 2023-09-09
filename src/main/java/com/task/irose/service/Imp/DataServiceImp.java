package com.task.irose.service.Imp;


import com.task.irose.dao.CurrencyRepository;
import com.task.irose.dao.ItemModelRepository;
import com.task.irose.exception.BusinessException;
import com.task.irose.model.currencys.ItemModel;
import com.task.irose.service.DataService;
import com.task.irose.service.pullData.PullDataService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp implements DataService {

    private final PullDataService pullDataService;

    private final CurrencyRepository currencyRepository;
    private final ItemModelRepository itemModelRepository;
    public DataServiceImp(PullDataService pullDataService, CurrencyRepository currencyRepository, ItemModelRepository itemModelRepository) {
        this.pullDataService = pullDataService;
        this.currencyRepository = currencyRepository;
        this.itemModelRepository = itemModelRepository;
    }


    @Override
    public String create() throws BusinessException {
        try {
            List<ItemModel> currencys = pullDataService.pullData();
//            currencys.forEach(
//                    itemModel ->
//                            itemModelRepository.save(itemModel)
//            );


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
