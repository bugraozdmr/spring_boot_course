package com.grant.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// FOR GLOBAL EXCEPTION HANDLER
@ControllerAdvice
public class StudentRestExceptionHandler {

    // bu sadece StudentNotFoundException yakalar
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handlerException(StudentNotFoundException exp) {

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exp.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    // bu genel exception yakalar
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handlerException(Exception exp) {
        StudentErrorResponse error = new StudentErrorResponse();

        //  ./dasod gibi burda ozel exception yazabilirsin yazda kullanıcıya bilgi verme guvenlik acigidir
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exp.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
