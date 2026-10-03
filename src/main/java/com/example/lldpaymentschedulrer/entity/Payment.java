package com.example.lldpaymentschedulrer.entity;

import com.example.lldpaymentschedulrer.constant.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Payment {
    private String paymentId;
    private LocalDateTime scheduledTime;
    private PaymentType paymentType;
    private BigDecimal amount;


}
