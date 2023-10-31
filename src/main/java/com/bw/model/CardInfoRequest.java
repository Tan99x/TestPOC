/**
 * 
 */
package com.bw.model;

import java.io.Serializable;

/**
 * 
 */
public class CardInfoRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8425096446573536815L;
	private String transactionType;
	private String amount;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	private CardDetails cardDetails;

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}

}
