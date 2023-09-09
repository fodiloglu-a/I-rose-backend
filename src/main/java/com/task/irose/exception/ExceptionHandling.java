package com.task.irose.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = ResponseException.class)
    public ResponseEntity responseException(ResponseException responseException){

        ExceptionResponse exceptionResponse=new ExceptionResponse(responseException.getMessage(),responseException.getStatus());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity responseException(BusinessException exception){

        ExceptionResponse exceptionResponse=new ExceptionResponse(exception.getMessage(),exception.getStatus());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
