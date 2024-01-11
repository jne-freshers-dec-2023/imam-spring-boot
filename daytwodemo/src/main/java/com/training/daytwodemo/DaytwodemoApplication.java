package com.training.daytwodemo;

import com.training.daytwodemo.book.Books;
import com.training.daytwodemo.configuration.ConfigureBeans;
import com.training.daytwodemo.student.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.training.daytwodemo")
public class DaytwodemoApplication {

    public static void main(String[] args) throws Exception {
        System.err.println("==========================Start of main() in DaytwodemoApplication=====================");
        Student student=new Student();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigureBeans.class);
        Books books1=context.getBean(Books.class);
        Books books2=context.getBean(Books.class);

        System.out.println("Books 1: "+books1);
        System.out.println("Books 2: "+books2);//book2 Printing Same as book1 Because scope is Singleton
        student.assignBook(books1);
        System.err.println("==========================End of main() in DaytwodemoApplication=====================");
    }

}


//Notes Based on Scenarios
//It will not destroy the Bean until it finishes all the process related to it