package com.aaa.service.claim.exception;


public class AccessDeniedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public AccessDeniedException(String message){
		super(message);
	}
	
	public AccessDeniedException(String message, Throwable throwable){
		super(message, throwable);
	}
}
