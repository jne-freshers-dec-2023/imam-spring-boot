package com.training.daytwodemo.configuration;

import com.training.daytwodemo.book.Books;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureBeans {

    @Bean
    public Books getBooks(){

        System.out.println("==========================Start of getBooks() in ConfigureBeans=====================");
        Books books =new Books();
        books.setId(101);
        books.setName("RichDadPoorDad");
        System.out.println("==========================End of getBooks() in ConfigureBeans=====================");
        return books;
    }
}
