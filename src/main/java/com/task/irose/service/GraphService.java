package com.task.irose.service;

import java.util.HashMap;
import java.util.Map;

public interface GraphService {
    void  putHash(Map<String, Object> objectMap , int count, HashMap<Integer,Double> graphValue);
    HashMap<Integer,Double> weekCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> monthCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> yearCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> fiveYearCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> allTimeCalculate(String ozanaka)throws RuntimeException;
}
