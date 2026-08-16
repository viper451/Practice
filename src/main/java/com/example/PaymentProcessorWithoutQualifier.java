package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// This class demonstrates what FAILS without @Qualifier
@Component
public class PaymentProcessorWithoutQualifier {
    
    // THIS WILL FAIL - Multiple beans of same type, no @Qualifier!
    // Spring doesn't know whether to inject CreditCardPayment or DebitCardPayment
    @Autowired
    private PaymentService paymentService;
    
    public void process() {
        paymentService.processPayment(50.0);
    }
}

