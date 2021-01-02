package com.kdmins.common.pojo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    public static void main(String[] args) throws InterruptedException {
        testsss a=new testsss();
        a.count=0;
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for(int i1 = 0; i1 < 10000; i1++) {


                a.insert();
            }
            System.out.println(a.count);


            /*
             */
        });
        threads[0].start();
            threads[1] = new Thread(() -> {
                for(int i1 = 0; i1 < 10000; i1++) {

                    a.add();

                }
                System.out.println(a.count);


/*
*/
            });
            threads[1].start();






/*
        countDownLatch.await();
*/

      /*  System.out.println(atomicInteger.get());*/
    }
}
