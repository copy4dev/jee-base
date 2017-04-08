package com.cn.jee.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 【强制】线程池不允许使用 Executors去创建，而是通过 ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。<br/>
 说明：Executors返回的线程池对象的弊端如下： <br/>
 1）FixedThreadPool和SingleThreadPool: <br/>
 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。 <br/>
 2）CachedThreadPool和ScheduledThreadPool: <br/>
 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。<br/>
 */
/**
 * 参考：http://dongxuan.iteye.com/blog/901689
 * 
 * @author 1002360
 * @version 2017年4月7日下午2:49:26 1002360 TODO
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        final int corePoolSize = 2;
        final int maximumPoolSize = 4;
        final long keepAliveTime = 1000L;
        final int queueSize = 2;

        ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());

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
