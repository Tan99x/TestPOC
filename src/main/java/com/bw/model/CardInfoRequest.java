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
	private CardDetails cardDetails;

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}

}
