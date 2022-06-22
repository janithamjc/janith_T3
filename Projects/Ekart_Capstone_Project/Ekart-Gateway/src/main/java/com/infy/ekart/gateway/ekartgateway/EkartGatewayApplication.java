package com.infy.ekart.gateway.ekartgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EkartGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartGatewayApplication.class, args);
	}

}
