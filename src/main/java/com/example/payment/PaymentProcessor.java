package com.example.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

// Class that autowires both implementations without @Qualifier
@Component
public class PaymentProcessor {
    
    // Injecting all implementations of PaymentService
//    @Autowired
//    private List<PaymentService> paymentServices;

    @Autowired
    private PaymentService paymentService;
    
    public void processAllPayments(double amount) {
        // for (PaymentService service : paymentServices) {
        //     System.out.println("Using: " + service.getPaymentMethod());
        //     service.processPayment(amount);
        // }
    }
    
    public void showAllPaymentMethods() {
        System.out.println("\n--- Available Payment Methods ---");
        // for (PaymentService service : paymentServices) {
        //     System.out.println("- " + service.getPaymentMethod());
        // }
    }
}

