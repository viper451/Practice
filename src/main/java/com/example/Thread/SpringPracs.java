package com.example.Thread;


class Worker {

   // private boolean running = true;
    private volatile boolean running = true;

    public void stop() {
        System.out.println("Thread 2: Setting running = false");
        running = false;
    }

    public void work() {
        System.out.println("Thread 1: Started working");

        while (running) {
            // Busy loop
            System.out.println("Thread 1: working");
        }

        System.out.println("Thread 1: Stopped working");
    }

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        Thread thread1 = new Thread(worker::work);
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            worker.stop();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Main: Both threads finished");
    }
}