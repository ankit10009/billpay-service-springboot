package com.example.billpay_service_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
