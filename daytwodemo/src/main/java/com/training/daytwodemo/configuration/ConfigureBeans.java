package com.training.daytwodemo.configuration;

import com.training.daytwodemo.book.Books;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigureBeans {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Books getBooks(){

        System.out.println("==========================Start of getBooks() in ConfigureBeans=====================");
        Books books =new Books();
        books.setId(101);
        books.setName("RichDadPoorDad");
        System.out.println("==========================End of getBooks() in ConfigureBeans=====================");
        return books;
    }
}
