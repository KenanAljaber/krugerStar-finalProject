package com.minegocio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NegocioRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NegocioRegistryServiceApplication.class, args);
	}

}
