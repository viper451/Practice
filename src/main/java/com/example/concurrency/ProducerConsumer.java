package com.example.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumer {
    Queue<Integer> queue = new LinkedList<>();
    int capacity = 5;

    ReentrantLock lock = new ReentrantLock();

    /*
     * Producer waits on this condition when the queue becomes FULL.
     * Once a consumer removes an item, it signals this condition,
     * allowing the producer to continue producing.
     */
    Condition notFull = lock.newCondition();

    /*
     * Consumer waits on this condition when the queue becomes EMPTY.
     * Once a producer adds an item, it signals this condition,
     * allowing the consumer to continue consuming.
     */
    Condition notEmpty = lock.newCondition();

    public void produce(int value) {
        lock.lock();

        try {
            while(queue.size() == capacity){
                System.out.println("SLEEPING NOT FULL THREAD");
                notFull.await();
            }
            queue.add(value);
            notEmpty.signalAll();
            System.out.println("VALUE ADDED "+ value);
            System.out.println("WAKING  CONSUMER THREAD");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


    }

    public void consume() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("SLEEPING NOT EMPTY THREAD");
                notEmpty.await();
            }
            System.out.println("CONSUME VALUE FROM QUEUE "+ queue.peek());
            System.out.println("Conusmed value successfully "+ queue.poll());
            notFull.signalAll();
        }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
            finally{
                lock.unlock();
            }
        System.out.println("SIZE OF QUEUE " + queue.size());
        }


    }

