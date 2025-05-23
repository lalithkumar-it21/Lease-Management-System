package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TenantManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantManagementApplication.class, args);
	}
//	
//	@LoadBalanced //for eureka server
//	@Bean
//	public RestTemplate getTemplate() {
//		return new RestTemplate();
//	}

}
