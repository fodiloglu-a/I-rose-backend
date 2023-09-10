package com.task.irose.service.pullData;


import com.task.irose.model.CurrencyModel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PullDataService {

    private final DownloadDataServices downloadDataServices;
    private final ParseXMLService parseXMLService;

    public PullDataService(DownloadDataServices downloadDataServices, ParseXMLService parseXMLService) {
        this.downloadDataServices = downloadDataServices;
        this.parseXMLService = parseXMLService;
    }


    public List<CurrencyModel> pullData() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String url = "https://www.bsi.si/_data/tecajnice/dtecbs-l.xml"; // XML sorce urllll

        String xmlData = downloadDataServices.downloadData(url); // XML downloadddddddd

        parseXMLService.db(xmlData); // XML Analizzzzzz

        return null;


    }


}
