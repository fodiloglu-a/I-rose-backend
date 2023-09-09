package com.task.irose.dao.xmlDao;

import com.task.irose.model.CurrencyModel;

import java.util.Date;
import java.util.List;

public interface XmlOperation {

    List<CurrencyModel>  getCurrencyByDate(Date date);
    List<CurrencyModel>  getCurrencyByOznaka(String oznaka);




}
