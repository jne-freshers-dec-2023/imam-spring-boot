package com.training.jdbcdemoforcrud.exception;

public class DepartmentAlreadyExistException extends RuntimeException{
    public DepartmentAlreadyExistException(String msg){
        super(msg);
    }
}
