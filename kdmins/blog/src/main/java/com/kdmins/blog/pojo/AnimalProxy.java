package com.kdmins.blog.pojo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class AnimalProxy  implements InvocationHandler {
    public AnimalProxy(Object object) {
        this.object = object;
    }
    private Object object;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------JDKProxy before------------");
        Object invoke = method.invoke(object, args);
        System.out.println("-----------JDKProxy after------------");
        return invoke;
    }

    public static void main(String[] args) {
        Dog dog=new Dog();
        AnimalProxy jdkProxy = new AnimalProxy(dog);
        Animal animal = (Animal) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Animal.class}, jdkProxy);
        animal.eat();
    }
}
