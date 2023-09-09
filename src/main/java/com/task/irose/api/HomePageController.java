package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.facet.dto.CurrencyDTO;
import com.task.irose.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomePageController {



    private final CurrencyService currencyService;

    public HomePageController(CurrencyService currencyService) {
        this.currencyService = currencyService;

    }

    @GetMapping
    public ResponseEntity<ResponseModel> gethomePage() throws ResponseException {

        ResponseModel responseModel=new ResponseModel();
        try {

            /**
             * maxDate="2023.09.07";
             */
            LocalDate now=LocalDate.of(2023,9,07);

            List<CurrencyDTO> currencyDTOS = currencyService.getByDatum(now);
            responseModel.setCurrencyDTOs(currencyDTOS);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (ResponseException exception){
            throw new ResponseException("get home page exception controller",HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/graph/{oznaka}")
    public ResponseEntity<ResponseModel> getModelByOznaka(@PathVariable String oznaka) throws ResponseException{
        ResponseModel responseModel=new ResponseModel();
        try {

            /**
             * maxDate="2023.09.07";
             */

           responseModel= currencyService.getGraph(oznaka);

            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (ResponseException exception){

            throw new ResponseException("get home page exception controller",HttpStatus.BAD_REQUEST);
        }

    }
}
