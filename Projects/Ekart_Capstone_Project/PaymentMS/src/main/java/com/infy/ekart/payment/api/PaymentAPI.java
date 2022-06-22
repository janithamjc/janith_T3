package com.infy.ekart.payment.api;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.infy.ekart.payment.service.PaymentCircuitBreakerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ekart.payment.dto.CardDTO;
import com.infy.ekart.payment.dto.OrderDTO;
import com.infy.ekart.payment.dto.TransactionDTO;
import com.infy.ekart.payment.exception.EKartPaymentException;
import com.infy.ekart.payment.exception.PayOrderFallbackException;
import com.infy.ekart.payment.service.PaymentService;


@CrossOrigin
@RestController
@RequestMapping(value = "/payment-api")
@Validated
public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentCircuitBreakerService paymentCircuitBreakerService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate template;

	private static final Log logger = LogFactory.getLog(PaymentAPI.class);

	@PostMapping(value = "/customer/{customerEmailId:.+}/cards")
	public ResponseEntity<String> addNewCard(@Valid @RequestBody CardDTO cardDTO,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+",
					message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartPaymentException, NoSuchAlgorithmException {
		logger.info("Recieved request to add new  card for customer : " + cardDTO.getCustomerEmailId());

		int cardId;
		cardId = paymentService.addCustomerCard(customerEmailId, cardDTO);
		String message = environment.getProperty("PaymentAPI.NEW_CARD_ADDED_SUCCESS");
		String toReturn = message + cardId;
		toReturn = toReturn.trim();
		return new ResponseEntity<>(toReturn, HttpStatus.OK);

	}

	@PutMapping(value = "/update/card")
	public ResponseEntity<String> updateCustomerCard(@Valid @RequestBody CardDTO cardDTO)
			throws EKartPaymentException, NoSuchAlgorithmException {
		logger.info("Recieved request to update  card :" + cardDTO.getCardId() + " of customer : "
				+ cardDTO.getCustomerEmailId());

		paymentService.updateCustomerCard(cardDTO);
		String modificationSuccessMsg = environment.getProperty("PaymentAPI.UPDATE_CARD_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@DeleteMapping(value = "/customer/{customerEmailId:.+}/card/{cardID}/delete")
	public ResponseEntity<String> deleteCustomerCard(@PathVariable("cardID") Integer cardID,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartPaymentException {
		logger.info("Recieved request to delete  card :" + cardID + " of customer : " + customerEmailId);

		paymentService.deleteCustomerCard(customerEmailId, cardID);
		String modificationSuccessMsg = environment.getProperty("PaymentAPI.CUSTOMER_CARD_DELETED_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	@GetMapping(value = "/customer/{customerEmailId}/card-type/{cardType}")
	public ResponseEntity<List<CardDTO>> getCardsOfCustomer(@PathVariable String customerEmailId,
			@PathVariable String cardType) throws EKartPaymentException {
		logger.info("Recieved request to fetch  cards of customer : " + customerEmailId + " having card type as: "
				+ cardType);

		List<CardDTO> cardDTOs = paymentService.getCustomerCardOfCardType(customerEmailId, cardType);
		return new ResponseEntity<>(cardDTOs, HttpStatus.OK);
	}

	
	@PostMapping(value = "/customer/{customerEmailId}/pay-order")
	// Annotate this method for handling resilience
	// Get the order details from CustomerMS for the given orderId (available in TransactionDTO)
	// Update the Transaction details with the obtained Order details in above step,
	// along with transaction date and total price
	// Authenticate the transaction details for the given customer by calling authenticatePayment() method of PaymentService
	// Add the transaction details to the database by calling addTransaction() method of PaymentService
	// Update the order status by calling updateOrderAfterPayment() method of PaymentCircuitBreakerService
	// Set the appropriate success or failure message and return the same
	public ResponseEntity<String> payForOrder(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+",
					message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId,
			@Valid @RequestBody TransactionDTO transactionDTO)
			throws NoSuchAlgorithmException, EKartPaymentException, PayOrderFallbackException {

				try {
					ResponseEntity<OrderDTO> orderResponse = template.getForEntity(
							"http://localhost:3336/Ekart/customerorder-api/order/" + transactionDTO.getOrder().getOrderId(),
							OrderDTO.class);

// Update the Transaction details with the obtained Order details in above step,
					transactionDTO.setTotalPrice(orderResponse.getBody().getTotalPrice());
					transactionDTO.setOrder(orderResponse.getBody());
					transactionDTO.setTransactionDate(LocalDateTime.now());
					// Authenticate the transaction details for the given customer by calling authenticatePayment() method of PaymentService
					transactionDTO =paymentService.authenticatePayment(customerEmailId,transactionDTO);
					//Add the transaction details to the database by calling addTransaction() method of PaymentService
					int status =paymentService.addTransaction(transactionDTO);
					// Update the order status by calling updateOrderAfterPayment() method of PaymentCircuitBreakerService
					paymentCircuitBreakerService.updateOrderAfterPayment(orderResponse.getBody().getOrderId(),"SUCCESS");
					// Set the appropriate success or failure message and return the same
					return ResponseEntity.ok().body("Success !");
				}catch (EKartPaymentException e){
					return ResponseEntity.unprocessableEntity().body("Payment Exception : "+e.getMessage());
				}catch (PayOrderFallbackException e){
					return ResponseEntity.internalServerError().body("Pay Order Failback : "+e.getMessage());
				}catch (Exception e){
					return ResponseEntity.unprocessableEntity().body("Error  : "+e.getMessage());
				}

	}
	
	//Implement a fallback method here
    //If exception message is Payment.TRANSACTION_FAILED_CVV_NOT_MATCHING then set message as Payment.TRANSACTION_FAILED_CVV_NOT_MATCHING
	//Else set the message as PaymentAPI.PAYMENT_FAILURE_FALLBACK
	//Return the above message as response
	public ResponseEntity<String> payForOrderFallback(String customerEmailId, TransactionDTO transactionDTO,
			RuntimeException exception) {
		//write your logic here
		return null;
	}
}
