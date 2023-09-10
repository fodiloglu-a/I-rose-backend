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


    CurrencyModel findByOznaka(String oznaka);
    @Query("SELECT c FROM CurrencyModel c WHERE c.oznaka = :oznaka AND c.datum >:datum order by c.exchangeRate")
    List<CurrencyModel> getModelForGraphQuery(@Param("oznaka") String oznaka,
                                              @Param("datum") LocalDate datum);

    @Query("SELECT SUM(c.exchangeRate) AS totalExchangeRate, COUNT(c) AS modelCount FROM CurrencyModel as c WHERE c.oznaka = :oznaka AND c.datum >= :datumStart AND c.datum <= :datumEnd")
    Map<String, Object> getTotalPriceAndModelCount(@Param("oznaka") String oznaka, @Param("datumStart") LocalDate datumStart, @Param("datumEnd") LocalDate datumEnd);


    @Query("SELECT c    FROM CurrencyModel as c  WHERE  c.datum >:date  group by c.sifra  ")

    List<CurrencyModel> getCurrencyByOznakaUniq(@Param("date") LocalDate date);

    @Query("SELECT c FROM CurrencyModel  as c WHERE  c.datum>:end and  c.datum <:start and c.oznaka=:oznaka order by c.datum")
    List<CurrencyModel> getCurrencyForCompare(LocalDate start, LocalDate end, String oznaka);
}
