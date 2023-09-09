package com.task.irose.service.pullData;

import com.task.irose.model.currencys.ItemModel;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;

@Component
public class PullDataService {

    private final DownloadDataServices downloadDataServices;
    private final ParseXMLService parseXMLService;

    public PullDataService(DownloadDataServices downloadDataServices, ParseXMLService parseXMLService) {
        this.downloadDataServices = downloadDataServices;
        this.parseXMLService = parseXMLService;
    }



    public List<ItemModel> pullData() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

            String url = "https://www.bsi.si/_data/tecajnice/dtecbs-l.xml"; // XML veri kaynağı URL'si

        String xmlData = downloadDataServices.downloadData(url); // XML verilerini indirme

        //List<ItemModel> currencys = parseXMLService.parseXML(xmlData); // XML verilerini analiz etme
          parseXMLService.db(xmlData); // XML verilerini analiz etme


        return null;




    }




}
