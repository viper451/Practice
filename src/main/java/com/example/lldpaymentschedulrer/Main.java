package com.example.lldpaymentschedulrer;

import com.example.lldpaymentschedulrer.constant.PaymentType;
import com.example.lldpaymentschedulrer.entity.Payment;
import com.example.lldpaymentschedulrer.entity.PaymentTask;
import com.example.lldpaymentschedulrer.service.PaymentProcessor;
import com.example.lldpaymentschedulrer.service.PaymentSchedulerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        LocalDateTime time1 = LocalDateTime.now().plusSeconds(4);
        LocalDateTime time2 = LocalDateTime.now().plusSeconds(3);
        LocalDateTime time3 = LocalDateTime.now().plusSeconds(2);
        LocalDateTime time4 = LocalDateTime.now().plusSeconds(2);

        Payment payment1 = new Payment("1", time1, PaymentType.UPI, BigDecimal.valueOf(22));
        Payment payment2 = new Payment("2", time2, PaymentType.UPI, BigDecimal.valueOf(52));
        Payment payment3 = new Payment("3", time3, PaymentType.UPI, BigDecimal.valueOf(42));
        Payment payment4 = new Payment("11", time3, PaymentType.UPI, BigDecimal.valueOf(12));


        PaymentSchedulerService scheduler =
                new PaymentSchedulerService(new PaymentProcessor());

        scheduler.schedulePayment(payment1);
        scheduler.schedulePayment(payment2);
        scheduler.schedulePayment(payment3);
        scheduler.schedulePayment(payment4);
    }
}


