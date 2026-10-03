package com.example.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    int count = 0;
    AtomicInteger atr = new AtomicInteger();

    ReentrantLock lock = new ReentrantLock();

    public void enterCounter() {
        //lock.lock();


        try {
            System.out.println("Thread  started by " + ' ' + Thread.currentThread().getName());
            for (int i = 0; i <= 999; i++) {
                atr.getAndIncrement();
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURED " + e);
        } finally {
            System.out.println("ABOUT TO ENLOCK: " + Thread.currentThread().getName());
       //     lock.unlock();
            System.out.println("Thread ended by " + ' ' + Thread.currentThread().getName());

        }
    }

    public void printCounter(){
        System.out.println("counter = "+ count);
        System.out.println("ATR = "+ atr);
    }
}

