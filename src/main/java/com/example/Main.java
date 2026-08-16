package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ratelimiter.RateLimiter;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Example 1: Using List without @Qualifier (collects all beans)
//        System.out.println("===== Example 1: List<PaymentService> (without @Qualifier) =====");
//        PaymentProcessor processor = context.getBean(PaymentProcessor.class);
//        processor.showAllPaymentMethods();
//        processor.processAllPayments(100.0);
//
//        // Example 2: Using individual fields with @Qualifier
//        System.out.println("\n===== Example 2: Individual fields with @Qualifier =====");
//        PaymentProcessorWithQualifier processorWithQualifier = context.getBean(PaymentProcessorWithQualifier.class);
//        processorWithQualifier.demonstrateQualifier();

//        SharedResource sharedResource = new SharedResource();
//
//        Thread t1 = new Thread(() -> {
//            sharedResource.producea();
//        });
//
//        Thread t2 = new Thread(() -> {
//            sharedResource.producea();
//        });
//
//        t1.start();
//        t2.start();

//        Counter counter = new Counter();
//
//        Thread t1 = new Thread(() -> {
//            counter.enterCounter();
//        });
//
//        Thread t2 = new Thread(() -> {
//            counter.enterCounter();
//        });
//
//        Thread t3 = new Thread(() -> {
//            counter.enterCounter();
//        });
//
//        Thread t4 = new Thread(() -> {
//            counter.enterCounter();
//        });
//
//        Thread t5 = new Thread(() -> {
//            counter.enterCounter();
//        });
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//           counter.printCounter();
//        try {
//            t5.join();
//System.out.println("axsx");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ProducerConsumer pc = new ProducerConsumer();
//
//        Thread producer = new Thread(() -> {
//        for(int i =0;i<2100;i++){
//            pc.produce(i);
//        }
//        });
//        Thread consumer = new Thread(() -> {
//            for(int i =0;i<2100;i++){
//                pc.consume();
//            }
//        });
//        long start = System.currentTimeMillis();
//
//
//        producer.start();
//        consumer.start();
//        producer.join();
//        consumer.join();
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("Execution Time: " + (end - start) + " ms");


        ReadWriteLock lock = new ReadWriteLock();

        Thread reader1 = new Thread(() -> {
            try {
                lock.lockRead();
                System.out.println("Reader 1 started");

                Thread.sleep(5000);

                System.out.println("Reader 1 finished");
                lock.unlockRead();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread reader2 = new Thread(() -> {
            try {
                lock.lockRead();
                System.out.println("Reader 2 started");

                Thread.sleep(5000);

                System.out.println("Reader 2 finished");
                lock.unlockRead();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000); // Let readers start first

                lock.lockWrite();
                System.out.println("Writer started");

                Thread.sleep(3000);

                System.out.println("Writer finished");
                lock.unlockWrite();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        reader1.start();
        reader2.start();
        writer.start();


        RateLimiter rateLimiter = new RateLimiter();


        Thread farhan = new Thread(() -> {
            try {
                while (true) {
                    rateLimiter.isAllowed("Farhan");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread sanya = new Thread(() -> {
            try {
                while (true) {
                    rateLimiter.isAllowed("Sanya");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        farhan.start();
        sanya.start();
        }


    }

