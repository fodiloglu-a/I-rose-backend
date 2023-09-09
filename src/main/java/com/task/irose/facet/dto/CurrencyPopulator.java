package com.task.irose.facet.dto;


import com.task.irose.facet.Converter;
import com.task.irose.model.CurrencyModel;
import org.springframework.stereotype.Component;

@Component
public class CurrencyPopulator implements Converter<CurrencyDTO, CurrencyModel> {

    @Override
    public CurrencyModel dtoToModel(CurrencyDTO currencyDTO) {
        try {
            CurrencyModel currencyModel=new CurrencyModel();
            currencyModel.setDatum(currencyDTO.getDatum());
            currencyModel.setExchangeRate(currencyDTO.getExchangeRate());
            currencyModel.setOznaka(currencyDTO.getOznaka());
            currencyModel.setSifra(currencyDTO.getSifra());
            return currencyModel;
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }

    }

    @Override
    public CurrencyDTO modelToDto(CurrencyModel currencyModel) {
        try {
            CurrencyDTO currencyDTO=new CurrencyDTO();
            currencyDTO.setDatum(currencyModel.getDatum());
            currencyDTO.setExchangeRate(currencyModel.getExchangeRate());
            currencyDTO.setOznaka(currencyModel.getOznaka());
            currencyDTO.setSifra(currencyModel.getSifra());
            return currencyDTO;

        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }

    }
    }

