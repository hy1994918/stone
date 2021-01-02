package com.kdmins.common;
public class XiaoMing {
   public void acceptAnswer(String answer){
         System.out.println("小明收到答案"+answer);
   }
   public void helpHong(XiaoHong xiaoHong) throws InterruptedException {
      System.out.println("小明开始求助小红");
      xiaoHong.acceptRequest(this,"1+1=?");
      doSomething();
   }
   public void doSomething(){
      System.out.println("小明做其他事去了");
   }

}
