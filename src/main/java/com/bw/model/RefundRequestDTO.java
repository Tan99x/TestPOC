/**
 * 
 */
package com.bw.model;

/**
 * 
 */
public class RefundRequestDTO {
	
	private String amount;
	private String transactionId;
	private String desc;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
