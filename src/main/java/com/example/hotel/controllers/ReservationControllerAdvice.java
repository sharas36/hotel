package com.example.hotel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = {ReservationController.class})
@RestController
public class ReservationControllerAdvice {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDetails> handle(Exception e){
        ErrorDetails error = new ErrorDetails("Custom Error", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = {Exception.class})
//    public ErrorDetails handle(Exception e){
//        return new ErrorDetails("Custom Error", e.getMessage());
//    }
}
