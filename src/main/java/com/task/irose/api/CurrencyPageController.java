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
    /**
     * sayfada iki adet para birimi seçilir
     * daha sonra bu seçimle birlikte tarih seçilir
     * tarihseçildikten sonra bu iki para birimi arasında hesaplamalar yapılır
     * ve bunlar grafik halinde kullanıcıya yanıstılır
     *
     */
    @PostMapping("/compare")

    public ResponseEntity<ResponseModel> compare(@RequestBody ResponseModel calculateRequestModel)throws ResponseException{
        try {
            ResponseModel percentage= calculateService.compare(calculateRequestModel);
            return ResponseEntity.status(HttpStatus.OK).body(percentage);
        }catch (ResponseException exception){
            throw new ResponseException("to compare get exception ", HttpStatus.BAD_GATEWAY);
        }
    }
    /*
        @GetMapping("/compare")
    public ResponseEntity<ResponseModel> compare(
            @RequestParam("startDate")LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("oznakaFirst") String oznakaFirst,
            @RequestParam("oznakaSecond") String oznakaSecond) throws ResponseException {
        try {

            ResponseModel requestModel = new ResponseModel();
            requestModel.setOznakaFirs(oznakaFirst);
            requestModel.setOznakaSecont(oznakaSecond);
            requestModel.setStartDate(startDate);
            requestModel.setEndDate(endDate);

            ResponseModel responseModel = calculateService.compare(requestModel);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception exception) {
            throw new ResponseException("to compare get exception ", HttpStatus.BAD_GATEWAY);
        }
    }


     */
}
