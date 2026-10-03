package com.example.lldpaymentschedulrer.service;

import com.example.lldpaymentschedulrer.entity.Payment;
import com.example.lldpaymentschedulrer.entity.PaymentTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentSchedulerService {

    private final DelayQueue<PaymentTask> delayQueue = new DelayQueue<>();

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(3);

    private final PaymentProcessor paymentProcessor;

    public PaymentSchedulerService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;

        startWorkers();
    }

    public void schedulePayment(Payment payment) {
        delayQueue.put(new PaymentTask(payment));
    }

    private void startWorkers() {
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                while (true) {
                    PaymentTask task = delayQueue.take();
                    paymentProcessor.process(task.getPayment());
                }
            });
        }
    }
}