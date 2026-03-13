package com.aaa.service.claim.exception;

public class NoClaimFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	public NoClaimFoundException(String message){
		super(message);
	}
	
	public NoClaimFoundException(String message, Throwable throwable){
		super(message, throwable);
	}
}
