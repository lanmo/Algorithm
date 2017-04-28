package com.yn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangnan on 17/4/27.
 */
public class TestGc {

    private Object lock = new Object();

    private Lock ll = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

//        List<Object> list = new ArrayList<Object>(100000);
//        for (int i=0; i<10000000;i++) {
//            list.add(new Object());
////            if (i % 10000 == 0) {
////                System.gc();
////            }
//        }
//
//        System.gc();
//
//        Thread.sleep(1000 * 60);
        TestGc t = new TestGc();
        t.tt();
    }

    public void tt() throws InterruptedException {
        synchronized (this) {
//            Thread.sleep(1000 * 60 * 10);
//            wait(1000 * 60 * 10);
            wait(1000 * 60 * 10);
        }

//        ll.lock();
//        try {
////            wait(1000 * 60 * 10);
////            wait();
////            Thread.currentThread().wait(1000 * 60 * 10);
////            ll.wait(1000 * 60 * 10);
//        } finally {
//            ll.unlock();
//        }
    }
}
