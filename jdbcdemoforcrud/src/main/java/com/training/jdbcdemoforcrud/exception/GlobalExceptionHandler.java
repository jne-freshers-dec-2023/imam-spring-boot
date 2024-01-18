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

    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public ResponseEntity<String> departmentAlreadyExistExceptionHandler(DepartmentAlreadyExistException departmentAlreadyExistException){
       return new ResponseEntity<>(departmentAlreadyExistException.getMessage(),HttpStatus.FOUND);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> departmentNotFoundExceptionHandler(DepartmentNotFoundException departmentNotFoundException){
        return new ResponseEntity<>(departmentNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> roleNotFoundExceptionHandler(RoleNotFoundException roleNotFoundException){
        return new ResponseEntity<>(roleNotFoundException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException runtimeException){
        return new ResponseEntity<>("Internal Server Error ",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
