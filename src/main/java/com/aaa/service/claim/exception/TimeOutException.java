package com.aaa.service.claim.exception;

public class TimeOutException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public TimeOutException(String message){
		super(message);
	}
	
	public TimeOutException(Throwable cause){	
		super(cause);
	}
	
	public TimeOutException(String message, Throwable cause){
		super(message, cause);
	}
}
