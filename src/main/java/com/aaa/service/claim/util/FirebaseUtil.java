package com.aaa.service.claim.util;

import com.aaa.service.claim.exception.AccessDeniedException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.http.HttpServletRequest;


public class FirebaseUtil {
	private final static String HEADER_BEARER = "Bearer ";
	private final static String MESSAGE_NO_TOKEN_FOUND = "No JWT token found in request headers";
	private final static int BEARER_START_INDEX = 7;
	
	
	public static FirebaseToken retrieveTokenFromRequestHeader(HttpServletRequest request, String tokenHeader) throws FirebaseAuthException, AccessDeniedException {
		String value = request.getHeader(tokenHeader);
		if (value == null) new AccessDeniedException(MESSAGE_NO_TOKEN_FOUND);
		return FirebaseAuth.getInstance().verifyIdToken(getAuthToken(value));
	}
	
	public static FirebaseToken retrieveTokenFromStringHeader(String token) throws AccessDeniedException, FirebaseAuthException {
		String value = getAuthToken(token);
		return FirebaseAuth.getInstance().verifyIdToken(value);
	}
	
	public static String retrieveIdFromRequestToken(HttpServletRequest request, String tokenHeader) throws AccessDeniedException, FirebaseAuthException {
		String value = request.getHeader(tokenHeader);
		return FirebaseAuth.getInstance().verifyIdToken(value).getUid();
	}
	
	public static String retrieveIdFromStringToken(String token) throws AccessDeniedException, FirebaseAuthException {
		String value = getAuthToken(token);
		return FirebaseAuth.getInstance().verifyIdToken(value).getUid();
	}
	
	private static String getAuthToken(String token) throws AccessDeniedException {
		if (token == null) return null;
		token = token.replaceAll("\"", "");
		validateHeader(token);
		return token.substring(BEARER_START_INDEX);
	}
	
	private static void validateHeader(String header) throws AccessDeniedException {
		if (header == null || !header.startsWith(HEADER_BEARER)) {
			throw new AccessDeniedException(MESSAGE_NO_TOKEN_FOUND);
		}
	}
}
