package com.example.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Main class for Payment Processing Module
 * Demonstrates different payment processing patterns:
 * - Basic payment processing
 * - Spring autowiring without @Qualifier
 * - Spring autowiring with @Qualifier
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Example 1: Using List without @Qualifier (collects all beans)
        System.out.println("===== Example 1: List<PaymentService> (without @Qualifier) =====");
        PaymentProcessor processor = context.getBean(PaymentProcessor.class);
        processor.showAllPaymentMethods();
        processor.processAllPayments(100.0);

        // Example 2: Using individual fields with @Qualifier
        System.out.println("\n===== Example 2: Individual fields with @Qualifier =====");
        PaymentProcessorWithQualifier processorWithQualifier = context.getBean(PaymentProcessorWithQualifier.class);
        processorWithQualifier.demonstrateQualifier();
        
        System.out.println("\n===== Example 3: Direct Service Usage =====");
        PaymentService creditCard = context.getBean("creditCardPayment", PaymentService.class);
        System.out.println("Payment Method: " + creditCard.getPaymentMethod());
        creditCard.processPayment(75.0);
    }
}

