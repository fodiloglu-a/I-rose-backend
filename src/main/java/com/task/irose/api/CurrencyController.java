package com.task.irose.api;


import com.task.irose.exception.ResponseException;
import com.task.irose.facet.dto.CurrencyDTO;
import com.task.irose.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;


    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * @return List<CurrencyDTO>
     * @throws ResponseException
     */
    @GetMapping("/currencys")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrency() throws ResponseException {
        try {
            List<CurrencyDTO> response= currencyService.getAllCurrency();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (ResponseException e){
            throw new ResponseException("getAllCurrency exception to GetMapping", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/{datum}")//localhost:8080/currency/?datum=01.02.2023
    public ResponseEntity<List<CurrencyDTO>> getByDatum(@PathVariable LocalDate datum) throws ResponseException{
        try {
            List<CurrencyDTO> currencyDTOS = currencyService.getByDatum(datum);
            return ResponseEntity.status(HttpStatus.OK).body(currencyDTOS);

        }catch (ResponseException e){
            throw new ResponseException("getByDatum exception to GetMapping", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/currency/{oznaka}")
    public ResponseEntity<CurrencyDTO> getByOznaka(@PathVariable(value = "{oznaka}") String oznaka) throws ResponseException{
        try {
            CurrencyDTO currencyDTO=currencyService.getByOznaka(oznaka);
            return ResponseEntity.status(HttpStatus.OK).body(currencyDTO);

        }catch (ResponseException e){
            throw new ResponseException("getByOznaka exception to GetMapping", HttpStatus.BAD_REQUEST);

        }
    }

}
