package com.casino.casinoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CasinoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasinoServiceApplication.class, args);
	}

}
