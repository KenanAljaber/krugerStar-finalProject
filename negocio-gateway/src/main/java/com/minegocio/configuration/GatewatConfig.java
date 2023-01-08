package com.minegocio.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewatConfig {
	
	@Bean
	public RouteLocator customRouteLocator (RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p-> p.path("/api/customers/**")
						.filters(f->{
							f.circuitBreaker(c->c.setName("user-service").setFallbackUri("forward:/customerFallback"));
							return f;
						}).uri("lb://user-service")).build();
		
		
	}

}
