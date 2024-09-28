package com.example.billpay_service_springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billpay_service_springboot.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
