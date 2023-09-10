package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.service.CalculateService;
import com.task.irose.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyPageController {

    private final CurrencyService currencyService;
    private final CalculateService calculateService;


    public CurrencyPageController(CurrencyService currencyService, CalculateService calculateService) {
        this.currencyService = currencyService;
        this.calculateService = calculateService;
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

    @PostMapping("/compare")

    public ResponseEntity<ResponseModel> compare(@RequestBody ResponseModel calculateRequestModel)throws ResponseException{
        try {
            ResponseModel percentage= calculateService.compare(calculateRequestModel);
            return ResponseEntity.status(HttpStatus.OK).body(percentage);
        }catch (ResponseException exception){
            throw new ResponseException("to compare get exception ", HttpStatus.BAD_GATEWAY);
        }
    }

}
