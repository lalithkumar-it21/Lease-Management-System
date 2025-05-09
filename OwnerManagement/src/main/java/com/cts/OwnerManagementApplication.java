package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class OwnerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnerManagementApplication.class, args);
	}

}
