package com.training.dayonedemo.customer;

import com.training.dayonedemo.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class Costumer {

    private int id;
    private String name;

    //Field Dependency Injection
    @Autowired
    private Transaction transaction;

    public Costumer() {}

    //Constructor Dependency Injection
    public Costumer(int id, String name, Transaction transaction) {
        this.id = id;
        this.name = name;
        this.transaction = transaction;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    //Setter Dependency Injection
    @Autowired
    public Transaction setterForTransaction() {
        System.out.println("Setter injection for Transaction in coustumer");
        return transaction;
    }
}