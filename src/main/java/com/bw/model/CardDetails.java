package com.bw.model;

import java.io.Serializable;

public class CardDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7951847843954912211L;
	private String cardholderName;
	private String cardNumber;
	private String expiryDate;
	private String securityCode;

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
