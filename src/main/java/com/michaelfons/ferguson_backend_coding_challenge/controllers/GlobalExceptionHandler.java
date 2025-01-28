package com.michaelfons.ferguson_backend_coding_challenge.controllers;

import com.michaelfons.ferguson_backend_coding_challenge.exceptions.DebuggingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DebuggingException.class)
    public ResponseEntity<String> handleDebuggingException(DebuggingException ex) {
        //ErrorResponse error = new ErrorResponseException(HttpStatusCode.valueOf(400), new Exception(""));
        //return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>("An debugging exception occurred: " + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
