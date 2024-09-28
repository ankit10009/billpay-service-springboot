package com.example.billpay_service_springboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payee {
    
    private Long id;
    private String payeeName;
    private String accountNumber;
    private String address;

    // Getters and Setters or use Lombok @Data annotation for brevity
}
