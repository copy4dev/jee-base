package com.cn.jee.test.thread;

/**
 * Runnable demo
 * 
 * @author 1002360
 * @version 2017年4月7日下午1:40:15 1002360 TODO
 */
public class ThreadTest implements Runnable {

    private String threadName;
    private long millis;

    public ThreadTest(String threadName, long millis) {
        this.threadName = threadName;
        this.millis = millis;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程 " + threadName + " 执行成功");
    }

}