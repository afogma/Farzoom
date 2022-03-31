package com.farzoom.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof TaskNotFoundException)
            return new ResponseEntity<>("Task for given id was not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}