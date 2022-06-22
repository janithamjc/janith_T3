package com.infy.ekart.payment.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {
	
	
	private String cardType;

	@Pattern(regexp = "^([0-9]){16}",message = "{invalid.cardnumber.pattern}")
	private String cardNumber;
	@Size(min = 5 , max = 50,message = "{invalid.cardname.length}")
	private String nameOnCard;
	private String hashCvv;
	@NotNull(message = "{invalid.cvv.notpresent}")
//	@Pattern(regexp = "^[0-9][0-9][0-9]",message = "{invalid.cvv.pattern}" )
	private Integer cvv;

	@Pattern(regexp = "^202[2-9]", message = "{card.year.incorrectyear}")
	private String expiryYear;

	@Pattern(regexp = "^([0-9][0-9])", message = "{card.month.incorrectmonth}")
	private String expiryMonth;

//	@NotNull(message = "{transaction.cardId.notpresent}")
	private Integer cardId;

	private String customerEmailId;
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getHashCvv() {
		return hashCvv;
	}
	public void setHashCvv(String hashCvv) {
		this.hashCvv = hashCvv;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
}
