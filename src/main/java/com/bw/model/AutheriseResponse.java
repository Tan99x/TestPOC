/**
 * 
 */
package com.bw.model;

import java.io.Serializable;

/**
 * 
 */
public class AutheriseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8425096446573536815L;
	private String errorCode;
	private String errorMsg;
	private String details;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
