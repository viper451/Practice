package com.example.lldpaymentschedulrer.service;

import com.example.lldpaymentschedulrer.entity.Payment;
import com.example.lldpaymentschedulrer.stratergy.PaymentStratergy;

public class PaymentProcessor implements PaymentStratergy {

    @Override
    public void process(Payment payment){

    System.out.println("PROCEESED PAYMENT WITH ID "+payment.getPaymentId());

    }


}
