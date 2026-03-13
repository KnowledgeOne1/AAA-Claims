package com.aaa.service.security;

import org.springframework.stereotype.Component;

import com.aaa.service.claim.exception.AccessDeniedException;

import jakarta.servlet.http.HttpServletRequest;


@Component
public interface Authenticate {
	
	public void checkAuthentication(HttpServletRequest request) throws AccessDeniedException;
	public void checkAuthentication(String tokenHeader) throws AccessDeniedException;
	
}
