package com.training.jdbcdemoforcrud.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String msg){
        super(msg);
    }
}
