package com.kdmins.blog.pojo;

public class Dog implements  Animal{
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}
