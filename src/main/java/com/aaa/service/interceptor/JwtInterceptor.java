package com.aaa.service.interceptor;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.HttpMethod;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aaa.service.security.Authenticate;
import com.aaa.service.claim.exception.AccessDeniedException;
import com.google.firebase.auth.FirebaseAuthException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {
	private final static String MESSAGE_AUTH_INVALID = "Authentication invalid";
	
	private final Authenticate auth;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessDeniedException, IOException {
		if (!request.getMethod().equalsIgnoreCase(HttpMethod.POST) &&
			!request.getMethod().equalsIgnoreCase(HttpMethod.GET)) {
			return true;
		}
		try {
			validateToken(request);
		} catch (FirebaseAuthException | AccessDeniedException e) {
			throw new AccessDeniedException(MESSAGE_AUTH_INVALID);
		}
		return true;
	}
	
	private void validateToken(HttpServletRequest request) throws FirebaseAuthException, AccessDeniedException, IOException {
		//this.auth.checkAuthentication(request);
	}
}
