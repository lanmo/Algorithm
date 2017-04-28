package com.yn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangnan on 17/4/18.
 */
public class TestExecutor {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));

        executor.execute(new MyThread());
        executor.execute(new MyThread());
        Thread.sleep(60 * 1000);
        executor.execute(new MyThread());
        Thread.sleep(2 * 60 * 1000);
        executor.execute(new MyThread());

//        executor.shutdown();
    }

    private static int a = 0;

    static class MyThread implements Runnable {

        public void run() {
            try {
                System.out.println(a++);
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
