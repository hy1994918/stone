package com.kdmins.common;

public class XiaoHong {
    XiaoMing xiaoMing;
    public void acceptRequest(XiaoMing xiaoMing,String hong) throws InterruptedException {
        System.out.println("小红收到问题"+hong);
        this.xiaoMing=xiaoMing;
        new Thread(() -> {
            try {
                helpMing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
    public void helpMing() throws InterruptedException {
        System.out.println("小红开始解决问题");
        Thread.sleep(10000);//模拟解决问题时间
        System.out.println("小红解决了问题");
        tellAnswer();
    }
    public void tellAnswer(){
        System.out.println();
        xiaoMing.acceptAnswer("2");
    }
}
