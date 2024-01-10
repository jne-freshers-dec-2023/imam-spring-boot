package com.training.dayonedemo;

import com.training.dayonedemo.customer.Costumer;
import com.training.dayonedemo.transaction.Deposit;
import com.training.dayonedemo.transaction.Transaction;
import com.training.dayonedemo.transaction.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DayonedemoApplication {

    @Autowired
    @Qualifier("Withdraw")
    Transaction transaction1;//It will refer Withdraw class as Qualifier "Withdraw"is provided
    @Autowired
    Transaction transaction2;//It will refer Deposit class as it is annotated with @Primary

    public static void runDemoTransactions(ConfigurableApplicationContext context) {
        //got the DayonedemoApplication bean from application context using getBean().
        DayonedemoApplication dayonedemoApplication1 = context.getBean(DayonedemoApplication.class);
        System.err.println("Printing transaction1 = " + context.getBean(Withdraw.class));
        System.err.println("Printing transaction1 = " + dayonedemoApplication1.transaction1);
        System.err.println("Printing transaction1 = " + dayonedemoApplication1.transaction2);
    }

    public static void runDemoCostumer(Costumer costumer) {
        System.out.println("Printing Costumer information=============");
        System.out.println("ID = " + costumer.getId());
        System.out.println("Name = " + costumer.getName());
        System.out.println("Transaction = " + costumer.getTransaction());
        System.out.println("Printing costumer reference in : runDemoCostumer:" + costumer);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DayonedemoApplication.class, args);
        runDemoTransactions(context);
        Costumer costumer = context.getBean("getCostumer", Costumer.class);
        Costumer costumerCDI = context.getBean("getCostumerCDI", Costumer.class);
        Costumer costumerSDI = context.getBean("getCostumerSDI", Costumer.class);
        runDemoCostumer(costumer);
        runDemoCostumer(costumerCDI);
        runDemoCostumer(costumerSDI);
    }

    @Bean
    public Costumer getCostumerSDI() {
        Costumer costumer = new Costumer();
        costumer.setter(new Deposit());
        return costumer;
    }

    @Bean
    public Costumer getCostumerCDI() {
        return new Costumer(10, "Imam", new Deposit());
    }

    @Bean
    public Costumer getCostumer() {
        return new Costumer();
    }
}
//Notes Based on Scenarios
//For collecting bean from App-Context can use getBean() else can use @Autowired Both referred same Bean
//If @Component is not given Bean will not be created and not able to find by @Autowired or getBean()
//If two beans found of same type then "required a single bean, but 2 were found:" in this we can use @Qualifier or @Primary
//Setter injection can set only Bean fields of the class not primitive or string fields