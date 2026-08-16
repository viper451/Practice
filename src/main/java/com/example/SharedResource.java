package com.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    ReentrantLock lock = new ReentrantLock();

    Condition checkh = lock.newCondition();

    public void producea() {
        lock.lock();

        try {
            System.out.println("Lock aqcuired by: " + Thread.currentThread().getName());
            Thread.sleep(5000);
            checkh.notifyAll();
        } catch(Exception e) {
            System.out.println("EXCEPTION OCCURED "+e);

        } finally {
            System.out.println("ABOUT TO ENLOCK: "+ Thread.currentThread().getName());
            lock.unlock();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }
}