package com.minegocio.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MyBeans {
	
	
	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate (){
		return new RestTemplate();
	}

}
