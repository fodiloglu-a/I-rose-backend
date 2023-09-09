package com.task.irose.service.pullData;

import com.task.irose.dao.CurrencyRepository;
import com.task.irose.model.CurrencyModel;
import com.task.irose.model.currencys.ItemModel;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Component
public class ParseXMLService {
    private final CurrencyRepository currencyRepository;

    public ParseXMLService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public   List<ItemModel> parseXML(String xmlData) {
        List<ItemModel> currencyList = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            NodeList nodeList = doc.getElementsByTagName("tecajnica");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String date = element.getAttribute("datum");

                    NodeList currencyNodes = element.getElementsByTagName("tecaj");

                    for (int j = 0; j < currencyNodes.getLength(); j++) {
                        Element currencyElement = (Element) currencyNodes.item(j);

                        String currencyCode = currencyElement.getAttribute("oznaka");
                        String sifra = currencyElement.getAttribute("sifra");
                        String exchangeRate = currencyElement.getTextContent();

                        ItemModel itemModel=selectModel(currencyCode);
                        itemModel.setDatum(convertStringToDate(date));
                        BigDecimal rate=new BigDecimal(exchangeRate);
                        itemModel.setExchangeRate(rate);
                        itemModel.setSifra(sifra);
                        itemModel.setOznaka(currencyCode);

                        currencyList.add(itemModel);
                    }

                }
            }
            return currencyList;

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public   void db(String xmlData) {


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));

            NodeList nodeList = doc.getElementsByTagName("tecajnica");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String date = element.getAttribute("datum");

                    NodeList currencyNodes = element.getElementsByTagName("tecaj");

                    for (int j = 0; j < currencyNodes.getLength(); j++) {
                        Element currencyElement = (Element) currencyNodes.item(j);

                        String currencyCode = currencyElement.getAttribute("oznaka");
                        String sifra = currencyElement.getAttribute("sifra");
                        String exchangeRate = currencyElement.getTextContent();

                        CurrencyModel currencyModel=new CurrencyModel();
                        //currencyModel.setDatum(convertStringToDate(date));
                        currencyModel.setOznaka(currencyCode);
                        currencyModel.setSifra(sifra);
                        currencyModel.setExchangeRate(new BigDecimal(exchangeRate));

                        currencyRepository.save(currencyModel);

                    }

                }
            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {

        }

    }
    public ItemModel selectModel(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String pck="com.task.irose.model.currencys."+className;
        Class<?> cls=Class.forName(pck);
        ItemModel o = (ItemModel) cls.newInstance();
        return o;
    }

    public   Date  convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }


}
