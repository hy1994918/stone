package com.kdmins.blog.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    java.util.TreeMap TreeMap;
    public static  ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HashMap HashMap;

        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
         String a="ssss";
         Integer b=3540160;
         System.out.println(a.hashCode()==b.hashCode());
         System.out.println(System.identityHashCode(a) == System.identityHashCode(b));
    }
}
