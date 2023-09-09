package com.task.irose.api;

import com.task.irose.exception.ResponseException;
import com.task.irose.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/create")
    public ResponseEntity create() throws ResponseException{
        try {


          String message=dataService.create();
          return ResponseEntity.status(HttpStatus.OK).body(message);

        }catch (ResponseException exception){
            throw new ResponseException("Post methodget exception ", HttpStatus.CREATED);
        }
    }
}
