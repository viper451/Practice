package com.example.FutureAsync;

import java.util.concurrent.CompletableFuture;

public class  Main {

    public static void main(String[] args) {

        System.out.println(
                Thread.currentThread().getName() + " -> Main started"
        );

        CompletableFuture<String> userFuture =
                CompletableFuture.supplyAsync(() -> {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " -> Fetching user..."
                    );

                    sleep(3000);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " -> User fetched"
                    );

                    return "Farhan";
                });

        CompletableFuture<String> orderFuture =
                CompletableFuture.supplyAsync(() -> {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " -> Fetching order..."
                    );

                    sleep(2000);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " -> Order fetched"
                    );

                    return "Order-123";
                });

        System.out.println(
                Thread.currentThread().getName()
                        + " -> Main thread continues..."
        );

        String user = userFuture.join();
        String order = orderFuture.join();

        System.out.println(
                Thread.currentThread().getName()
                        + " -> User = " + user
        );

        System.out.println(
                Thread.currentThread().getName()
                        + " -> Order = " + order
        );

        System.out.println(
                Thread.currentThread().getName()
                        + " -> Main finished"
        );
    }

    static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}