package com.training.jdbcdemoforcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> studentNotFoundExceptionHandler(StudentNotFoundException studentNotFoundException) {
        return new ResponseEntity<>(studentNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> departmentNotFoundExceptionHandler(DepartmentNotFoundException departmentNotFoundException){
       return new ResponseEntity<>(departmentNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

}
