package com.example.concurrency;

/**
 * Main class for Concurrency Module
 * Demonstrates various concurrency patterns:
 * - Thread-safe counters with atomic operations
 * - Read-Write locks
 * - Shared resources with locks
 * - Producer-Consumer pattern
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Example 1: Counter with Atomic Operations
        System.out.println("===== Example 1: Thread-Safe Counter =====");
        demonstrateCounter();

        // Example 2: Read-Write Lock
        System.out.println("\n===== Example 2: Read-Write Lock =====");
        demonstrateReadWriteLock();

        // Example 3: Shared Resource with Locks
        System.out.println("\n===== Example 3: Shared Resource with Locks =====");
        demonstrateSharedResource();

        // Example 4: Producer-Consumer Pattern
        System.out.println("\n===== Example 4: Producer-Consumer Pattern =====");
        demonstrateProducerConsumer();
    }

    /**
     * Demonstrates counter with atomic operations and multiple threads
     */
    private static void demonstrateCounter() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(counter::enterCounter, "Thread-1");
        Thread t2 = new Thread(counter::enterCounter, "Thread-2");
        Thread t3 = new Thread(counter::enterCounter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        counter.printCounter();
    }

    /**
     * Demonstrates read-write lock with multiple readers and writers
     */
    private static void demonstrateReadWriteLock() throws InterruptedException {
        ReadWriteLock lock = new ReadWriteLock();

        Thread reader1 = new Thread(() -> {
            try {
                lock.lockRead();
                System.out.println("Reader 1 started");
                Thread.sleep(2000);
                System.out.println("Reader 1 finished");
                lock.unlockRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Reader-1");

        Thread reader2 = new Thread(() -> {
            try {
                lock.lockRead();
                System.out.println("Reader 2 started");
                Thread.sleep(2000);
                System.out.println("Reader 2 finished");
                lock.unlockRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Reader-2");

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(500); // Let readers start first
                lock.lockWrite();
                System.out.println("Writer started");
                Thread.sleep(1000);
                System.out.println("Writer finished");
                lock.unlockWrite();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Writer");

        reader1.start();
        reader2.start();
        writer.start();

        reader1.join();
        reader2.join();
        writer.join();
    }

    /**
     * Demonstrates shared resource with reentrant locks
     */
    private static void demonstrateSharedResource() throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(() -> {
            sharedResource.producea();
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            sharedResource.producea();
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    /**
     * Demonstrates producer-consumer pattern with queue
     */
    private static void demonstrateProducerConsumer() throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.produce(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pc.consume();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        long start = System.currentTimeMillis();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        long end = System.currentTimeMillis();

        System.out.println("\nExecution Time: " + (end - start) + " ms");
    }
}

