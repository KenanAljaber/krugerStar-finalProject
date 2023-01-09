package com.minegocio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	
	@RequestMapping("/customerFallback")
	public Mono<String> customerFallback(){
		return Mono.just("Customer service is taking too long to execute,"
				+ " please try again later");
	}

	@RequestMapping("/casinoFallback")
	public Mono<String> casinoFallback(){
		return Mono.just("Casino service is taking too long to execute,"
				+ " please try again later");
	}
	

}
