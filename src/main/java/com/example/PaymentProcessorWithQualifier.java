package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// This class demonstrates the CORRECT way with @Qualifier
// When you need a single bean of type PaymentService, use @Qualifier
@Component
public class PaymentProcessorWithQualifier {
    
    // THIS WORKS - Using @Qualifier to specify which bean to inject
    @Autowired
    private PaymentService creditCardPayment;
    
    @Autowired
    private PaymentService debitCardPayment;
    
    public void demonstrateQualifier() {
        System.out.println("\n=== Using @Qualifier ===");
        System.out.println("Credit Card: " + creditCardPayment.getPaymentMethod());
        creditCardPayment.processPayment(50.0);
        
        System.out.println("\nDebit Card: " + debitCardPayment.getPaymentMethod());
        debitCardPayment.processPayment(50.0);
    }
}

