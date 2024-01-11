package com.training.dayonedemo.transaction;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("Deposit")
@Primary
public class Deposit implements Transaction {
    @Override
    public void doTransaction() {
        System.out.println("Amount Deposited");
    }
}
