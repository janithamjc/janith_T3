package com.infy.ekart.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PaymentCircuitBreakerService {
		
	@Autowired
	private RestTemplate template;
	// Add necessary CircuitBreaker annotation

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

	public void updateOrderAfterPayment(Integer orderId,String transactionStatus) {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("customer-order");
	//	template.put("http://localhost:3336/Ekart/customerorder-api/order/"+orderId+"/update/order-status", transactionStatus);
//		template.put("http://customerMS/Ekart/customerorder-api/order/"+orderId+"/update/order-status", transactionStatus);
		HttpHeaders headers = new HttpHeaders();

		HttpEntity httpEntity = new HttpEntity(transactionStatus,headers);
		circuitBreaker.run(()-> template.exchange("http://localhost:3336/Ekart/customerorder-api/order/"+orderId+"/update/order-status",
				HttpMethod.POST,httpEntity,String.class));


	}
}
