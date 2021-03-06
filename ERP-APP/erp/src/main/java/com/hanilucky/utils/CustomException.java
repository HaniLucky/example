package com.hanilucky.utils;


/**
 * 自定义异常
 */
public class CustomException extends Exception{

	private static final long serialVersionUID = -8058196891080415658L;
	
	private String message ;
	

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	
}
