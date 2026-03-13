package com.aaa.service.claim.exception;

public class ClaimException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	public ClaimException(Exception e){
		super(e);
	}
	
	public ClaimException(String message, Throwable throwable){
		super(message, throwable);
	}
}
