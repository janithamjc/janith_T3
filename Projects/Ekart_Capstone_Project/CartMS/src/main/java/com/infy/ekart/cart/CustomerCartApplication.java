package com.infy.ekart.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
@EnableEurekaClient
public class CustomerCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCartApplication.class, args);
	}

}
