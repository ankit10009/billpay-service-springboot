package com.example.billpay_service_springboot.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.billpay_service_springboot.entity.Account;
import com.example.billpay_service_springboot.entity.Payee;
import com.example.billpay_service_springboot.entity.Payment;
import com.example.billpay_service_springboot.repo.PaymentRepository;

@Service
public class BillPayService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${account.service.url}")
    private String accountServiceUrl;

    @Value("${payee.service.url}")
    private String payeeServiceUrl;

    public Payment makePayment(Long payeeId, Long accountId, BigDecimal amount) {
        // Validate payee exists
        ResponseEntity<Payee> payeeResponse = restTemplate.getForEntity(payeeServiceUrl + "/payees/" + payeeId, Payee.class);
        if (!payeeResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Payee not found");
        }

        // Validate account exists and is active
        ResponseEntity<Account> accountResponse = restTemplate.getForEntity(accountServiceUrl + "/accounts/" + accountId, Account.class);
        if (!accountResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Account not found");
        }

        // Check balance
        ResponseEntity<BigDecimal> balanceResponse = restTemplate.getForEntity(accountServiceUrl + "/accounts/" + accountId + "/balance", BigDecimal.class);
        if (balanceResponse.getBody().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct the amount
        restTemplate.put(accountServiceUrl + "/accounts/" + accountId + "/balance", balanceResponse.getBody().subtract(amount));

        // Record the payment
        Payment payment = new Payment();
        payment.setPayeeId(payeeId);
        payment.setAccountId(accountId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentHistory() {
        return paymentRepository.findAll();
    }
}

