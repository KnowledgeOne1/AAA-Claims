package com.aaa.service.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.aaa.service.claim.exception.AccessDeniedException;
import com.aaa.service.claim.util.FirebaseUtil;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class FirebaseAuthenticate implements Authenticate {
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	
	public void checkAuthentication(HttpServletRequest request) throws AccessDeniedException {
		try {
			FirebaseToken token = FirebaseUtil.retrieveTokenFromRequestHeader(request, this.tokenHeader);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, null);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			throw new AccessDeniedException("");
		}
	}
	
	public void checkAuthentication(String request) throws AccessDeniedException {
		try {
			FirebaseToken token = FirebaseUtil.retrieveTokenFromStringHeader(request);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, null);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			throw new AccessDeniedException("");
		}
	}
}
