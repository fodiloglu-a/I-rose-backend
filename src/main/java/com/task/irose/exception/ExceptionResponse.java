package com.task.irose.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private String message;
    private HttpStatus status;

    public ExceptionResponse(String message, HttpStatus status) {

        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
