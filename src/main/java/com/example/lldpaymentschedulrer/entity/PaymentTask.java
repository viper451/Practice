package com.example.lldpaymentschedulrer.entity;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
public class PaymentTask implements Delayed {

    private final Payment payment;

    public PaymentTask(Payment payment) {
        this.payment = payment;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Duration duration =
                Duration.between(LocalDateTime.now(), payment.getScheduledTime());

        return unit.convert(duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        PaymentTask  paymentTask =  (PaymentTask) other;
        return this.payment.getScheduledTime()
                .compareTo(paymentTask.payment.getScheduledTime());
    }
}
