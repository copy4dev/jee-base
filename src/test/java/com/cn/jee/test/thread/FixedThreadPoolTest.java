package com.cn.jee.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建固定大小的线程池。<br/>
 * 每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。<br/>
 * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。<br/>
 * 可以并发运行线程池大小以内的线程
 */
/**
 * 定长线程池 demo
 * 
 * @author 1002360
 * @version 2017年4月7日下午1:26:05 1002360 TODO
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        // 线程池大小
        final int poolSize = 2;

        // 创建固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        ThreadTest t1 = new ThreadTest("t1", 1000 * 1);
        ThreadTest t2 = new ThreadTest("t2", 1000 * 2);
        ThreadTest t3 = new ThreadTest("t3", 1000 * 3);
        ThreadTest t4 = new ThreadTest("t4", 1000 * 4);
        ThreadTest t5 = new ThreadTest("t5", 1000 * 5);

        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        // 启动一次后关闭，执行以前提交的任务，但不接受新任务
        pool.shutdown();

        // 设置主线程等待其子线程执行完毕
        while (true) {
            if (pool.isTerminated()) {
                // 当所有的子线程结束后，完成主线程
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("所有线程执行完毕");

    }

}
