package com.training.daytwodemo.student;

import com.training.daytwodemo.book.Books;

public class Student {

    public void assignBook(Books books) throws Exception {

        System.out.println("==========================Start of assignBook() in Student=====================");
        System.out.println("Printing Reference of Collected Books Bean from App context: in assignBook()");
       // books.preDestroyfunction();
        //books.destroy();
        System.out.println("Printing Reference of Destroyed Books Bean from App context: in assignBook()");
        System.out.println("==========================End of assignBook() in Student=====================");
    }

}
