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
//        if (ex instanceof SectionAlreadyExistsException)
//            return new ResponseEntity<>("Section already exists", new HttpHeaders(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Other error", HttpStatus.I_AM_A_TEAPOT);
    }
}