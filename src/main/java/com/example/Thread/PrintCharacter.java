package com.example.Thread;

public class PrintCharacter {

    int turn = 1;

    // 1 -> A
    // 2 -> B
    // 3 -> C

    Object lock = new Object();
    int count = 0;

    Thread a1 = new Thread(this::PrintA);
    Thread b1 = new Thread(this::PrintB);
    Thread c1 = new Thread(this::PrintC);

    public void PrintA() {

        while (count < 3) {
            synchronized (lock) {

                if (turn == 1) {
                    System.out.print("A ");
                    turn++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    public void PrintB() {

        while (count < 3) {
            synchronized (lock) {

                if (turn == 2) {
                    System.out.print("B ");
                    turn++;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    public void PrintC() {

        while (count < 3) {
            synchronized (lock) {

                if (turn == 3) {
                    System.out.println("C ");
                    count++;
                    turn = 1;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        PrintCharacter pc = new PrintCharacter();

        pc.a1.start();
        pc.b1.start();
        pc.c1.start();

        pc.a1.interrupt();
        pc.b1.interrupt();
        pc.c1.interrupt();
    }
}