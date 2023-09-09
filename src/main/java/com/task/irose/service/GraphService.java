package com.task.irose.service;

import java.util.HashMap;

public interface GraphService {

    HashMap<Integer,Double> weekCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> monthCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> yearCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> fiveYearCalculate(String ozanaka)throws RuntimeException;
    HashMap<Integer,Double> allTimeCalculate(String ozanaka)throws RuntimeException;
}
