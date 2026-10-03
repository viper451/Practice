package com.example.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Second implementation of PaymentService
@Service
public class DebitCardPayment implements PaymentService {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Debit Card");
    }
    
    @Override
    public String getPaymentMethod() {
        return "Debit Card";
    }
}

