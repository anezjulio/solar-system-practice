package com.ml.forecasttest.controller;

import com.ml.forecasttest.exception.BadRequestException;
import com.ml.forecasttest.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(
        		new ErrorResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
				new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadData(BadRequestException bre) {
        LOGGER.error(bre.getMessage(), bre);
        return new ResponseEntity<>(
        		new ErrorResponse(400, HttpStatus.BAD_REQUEST, bre.getMessage()),
				new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
    }
}
