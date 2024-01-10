package com.training.dayonedemo.transaction;

import org.springframework.stereotype.Component;

@Component("Withdraw")
public class Withdraw implements Transaction{
    @Override
    public void doTransaction() {
        System.out.println("Amount Withdrawn");
    }
}
