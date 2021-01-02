package com.kdmins.stay.controller;

import java.util.ArrayList;
import java.util.List;

//资源类
public class MyStack {


    public static void main(String[] args) throws InterruptedException {
        MyStack myStack = new MyStack();

        P_Thread pThread1 = new P_Thread(myStack, "P1");

        pThread1.start();


        C_Thread cThread1 = new C_Thread(myStack, "C1");
        C_Thread cThread2 = new C_Thread(myStack, "C2");

        cThread1.start();
        cThread2.start();

    }


    // 共享队列
    private List list = new ArrayList();

    // 生产
    @SuppressWarnings("unchecked")
    public synchronized void push() {
        try {
            while (list.size() == 10) {    // 多个生产者
                System.out.println("队列已满，线程 "
                        + Thread.currentThread().getName() + " 呈wait状态...");
                this.wait();
            }
            list.add("anyString=" + Math.random());
            System.out.println("线程 " + Thread.currentThread().getName()
                    + " 生产了，"+list.size());
            System.out.println();
            this.notifyAll();                   // 防止生产者仅通知生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 消费
    public synchronized String pop() {
        String returnValue = "";
        try {
            while (list.size() == 0) {              // 多个消费者
                System.out.println("队列已空，线程 "
                        + Thread.currentThread().getName() + " 呈wait状态...");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            System.out.println("线程 " + Thread.currentThread().getName()
                    + " 消费了，队列已空...");
            this.notifyAll();                   // 防止消费者仅通知消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

//生产者
class P_Thread extends Thread {

    private MyStack myStack;

    public P_Thread(MyStack myStack,String name) {
        super(name);
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }

    @Override
    public void run() {
        while (true) {
            myStack.push();
        }
    }
}

//消费者
class C_Thread extends Thread {

    private MyStack myStack;

    public C_Thread(MyStack myStack,String name) {
        super(name);
        this.myStack = myStack;
    }

    @Override
    public void run() {
        while (true) {
            myStack.pop();
        }
    }
}

