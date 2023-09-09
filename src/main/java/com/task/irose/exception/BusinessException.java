package com.task.irose.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
    private String message;
    private HttpStatus status;




    public BusinessException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}