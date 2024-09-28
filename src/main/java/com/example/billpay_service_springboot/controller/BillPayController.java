package com.example.billpay_service_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billpay_service_springboot.entity.Payment;
import com.example.billpay_service_springboot.entity.PaymentRequest;
import com.example.billpay_service_springboot.service.BillPayService;

@RestController
@RequestMapping("/billpay")
public class BillPayController {

    @Autowired
    private BillPayService billPayService;

    @PostMapping("/payments")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(billPayService.makePayment(paymentRequest.getPayeeId(), paymentRequest.getAccountId(), paymentRequest.getAmount()), HttpStatus.CREATED);
    }

    @GetMapping("/payments/history")
    public ResponseEntity<List<Payment>> getPaymentHistory() {
        return new ResponseEntity<>(billPayService.getPaymentHistory(), HttpStatus.OK);
    }
}