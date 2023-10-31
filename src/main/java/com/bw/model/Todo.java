package com.bw.model;

/**
 * Todo.java This is a model class represents a Todo entity
 * 
 *
 */
public class Todo {

	private Long id;
	private String cardNumber;
	private String cardExpiry;
	private String cvv;
	private String cardHolderName;
	private String amount;

	public Todo(Long id, String cardNumber, String cardExpiry, String cvv, String cardHolderName, String amount) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		this.cardHolderName = cardHolderName;
		this.amount = amount;
	}

	public Todo(String cardNumber, String cardExpiry, String cvv, String cardHolderName, String amount) {
		super();
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		this.cardHolderName = cardHolderName;
		this.amount = amount;
	}

	protected Todo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

}
