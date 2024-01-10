package com.training.daytwodemo.book;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Books implements InitializingBean, DisposableBean {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("==========================Start of destroy() in Books=====================");
        this.id=0;
        this.name=null;
        System.err.println("Destroying Bean of book class");
        System.err.println("Books  id = "+id );
        System.err.println("Books  Name = "+name );
        System.err.println("==========================End of destroy() in Books=====================");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==========================Start of afterPropertiesSet() in Books=====================");
        System.out.println("Bean ia created for book class");
        System.out.println("Books  id = "+id );
        System.out.println("Books  Name = "+name );
        System.out.println("==========================End of afterPropertiesSet() in Books=====================");
    }
}
