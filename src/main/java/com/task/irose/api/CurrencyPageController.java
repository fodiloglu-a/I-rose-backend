package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.service.CurrencyService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyPageController {

    private final CurrencyService currencyService;


    public CurrencyPageController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getPage() throws ResponseException {
        ResponseModel responseModel=new ResponseModel();

        try {
            ResponseModel response=currencyService.getCurrencyPage();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (ResponseException exception){

            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseModel);
        }

    }
}
