package com.xuran.kafka_study.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author XuRan
 * @Date 2022/3/15 9:45
 * @Version 1.0
 * @Description 进程
 */
public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*线程池*/
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("i = " + i);
                }
            }
        });

        future.get(); //阻塞

        System.out.println("=================================");

        executor.shutdown();

    }

}
