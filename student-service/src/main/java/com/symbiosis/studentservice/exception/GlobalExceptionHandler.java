package com.symbiosis.studentservice.exception;

import com.symbiosis.studentservice.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponse> globalExceptionHandler(GlobalException globalException) {
        return new ResponseEntity<>(new ExceptionResponse(globalException.message, globalException.httpStatus, globalException.timeStamp, globalException.details), globalException.httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> runtimeExceptionHandler(Exception runtimeException) {
        return new ResponseEntity<>("Internal Server Error ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> runtimeExceptionHandler(MissingServletRequestParameterException exception) {
        return new ResponseEntity<>("Missing Data in The Request", HttpStatus.BAD_REQUEST);
    }
}