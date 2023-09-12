package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
public class GraphPageController {
    private final CurrencyService currencyService;

    public GraphPageController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{oznaka}")
    public ResponseEntity<ResponseModel> getModelByOznaka(@PathVariable String oznaka) throws ResponseException {
        ResponseModel responseModel=new ResponseModel();
        try {

            /**
             * maxDate="2023.09.07";
             */

            responseModel= currencyService.getGraph(oznaka.toUpperCase());

            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (ResponseException exception){

            throw new ResponseException("get home page exception controller",HttpStatus.BAD_REQUEST);
        }

    }
}
