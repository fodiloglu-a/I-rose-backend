package com.task.irose.dao;


import com.task.irose.facet.dto.CurrencyDTO;
import com.task.irose.model.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyModel,Long> {



    List<CurrencyModel>  findCurrencyModelByDatum(LocalDate datum);

    CurrencyModel findCurrencyModelByDatumAndOznaka(LocalDate date,String ozanaka);


    CurrencyModel findByOznaka(String oznaka);

    @Query("SELECT c FROM CurrencyModel c WHERE c.oznaka = :oznaka AND c.datum > :datumStart AND c.datum < :datumEnd ORDER BY c.datum")
    List<CurrencyModel> getCurrencyModelByOznakaAndDatum(@Param("datumStart")Date datumStart ,
                                                         @Param("datumEnd") Date datumEnd,
                                                         @Param("oznaka") String oznaka);




    @Query("SELECT c FROM CurrencyModel c WHERE c.oznaka = :oznaka AND c.datum >:datum order by c.exchangeRate")
    List<CurrencyModel> getModelForGraphQuery(@Param("oznaka") String oznaka,
                                              @Param("datum") LocalDate datum);
/*
SELECT SUM(c.model_exchange_rate) AS totalExchangeRate, COUNT(*) AS modelCount
FROM currency_model AS c
WHERE c.model_oznaka = 'USD'
      AND c.model_datum >= '2023-09-01'
      AND c.model_datum <= '2023-09-08';

 */
    @Query("SELECT SUM(c.exchangeRate) AS totalExchangeRate, COUNT(c) AS modelCount FROM CurrencyModel as c WHERE c.oznaka = :oznaka AND c.datum >= :datumStart AND c.datum <= :datumEnd")
    Map<String, Object> getTotalPriceAndModelCount(@Param("oznaka") String oznaka, @Param("datumStart") LocalDate datumStart, @Param("datumEnd") LocalDate datumEnd);


    @Query("SELECT c    FROM CurrencyModel as c  WHERE  c.datum >:date  group by c.sifra  ")

    List<CurrencyModel> getCurrencyByOznakaUniq(@Param("date") LocalDate date);
}
