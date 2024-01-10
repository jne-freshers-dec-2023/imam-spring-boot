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
        Books books=context.getBean(Books.class);
        student.assignBook(books);
        System.err.println("==========================End of main() in DaytwodemoApplication=====================");
        student.assignBook(books);
    }

}


//Notes Based on Scenarios
//It will not destroy the Bean until it finishes all the process related to it