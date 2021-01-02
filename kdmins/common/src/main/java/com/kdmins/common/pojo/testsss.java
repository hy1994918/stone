package com.kdmins.common.pojo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testsss {
    Lock lock = new ReentrantLock();    //注意这个地方

    Integer count;
    Integer insert=0;
   void  add(){
       lock.lock();
       try {
           if( lock.tryLock(1000, TimeUnit.MILLISECONDS)){
               try{
                   count++;
               }finally {
                   lock.unlock();
               }
           }else{
               System.out.println(Thread.currentThread().getName()+"获取锁失败");
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }


   };

    void  insert(){
        lock.unlock();



    };
}
