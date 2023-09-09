package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.facet.ResponseModel;
import com.task.irose.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/compare")

    public ResponseEntity compare(@RequestBody ResponseModel calculateRequestModel)throws ResponseException{
        try {
           double percentage= calculateService.compare(calculateRequestModel);
           return ResponseEntity.status(HttpStatus.OK).body(percentage);
        }catch (ResponseException exception){
            throw new ResponseException("to compare get exception ", HttpStatus.BAD_GATEWAY);
        }
    }

}
