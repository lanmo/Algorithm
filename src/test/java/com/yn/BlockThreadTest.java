package com.yn;

/**
 * Created by yangnan on 17/4/20.
 */
public class BlockThreadTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void lock1() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(60 * 1000);
            synchronized (lock2) {
                System.out.println("lock1");
            }
        }
    }

    public static void lock2() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(60 * 1000);
            synchronized (lock1) {
                System.out.println("lock2");
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    BlockThreadTest.lock1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "test1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    BlockThreadTest.lock2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "test2");

//        t.start();
//        t2.start();
    }
}
