package com.training.dayonedemo.customer;

import com.training.dayonedemo.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class Costumer {

    private int id;
    private String name;
    private Transaction transaction;

    @Autowired
    public Costumer() {
        id = 0;
        name = "";
        transaction = null;
    }

    //Constructor Dependency Injection can
    @Autowired
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
    public void setter(Transaction transaction) {
        this.transaction = transaction;
    }
}