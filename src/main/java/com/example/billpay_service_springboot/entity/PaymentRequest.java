package com.example.billpay_service_springboot.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long payeeId;
    private Long accountId;
    private BigDecimal amount;

    // Getters and Setters
}

