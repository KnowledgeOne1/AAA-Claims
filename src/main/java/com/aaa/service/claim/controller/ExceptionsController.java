package com.aaa.service.claim.controller;

import static com.aaa.service.claim.util.StatusConstants.STATUS_CODE_CLAIM_NOT_FOUND;
import static com.aaa.service.claim.util.StatusConstants.STATUS_MESSAGE_CLAIM_NOT_FOUND;
import static com.aaa.service.claim.util.StatusConstants.STATUS_CODE_CONSTRAINT_ERROR;
import static com.aaa.service.claim.util.StatusConstants.STATUS_MESSAGE_CONSTRAINT_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aaa.service.claim.exception.ClaimException;
import com.aaa.service.claim.exception.NoClaimFoundException;
import com.aaa.service.claim.response.Response;
import com.aaa.service.claim.response.Status;
import com.aaa.service.claim.response.ClaimResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
class GlobalControllerExceptionHandler {
	
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public void handleConflict(Exception e) throws ClaimException {
    	log.error(e.getMessage(), e);
        throw new ClaimException(e);
    }
    
    @ExceptionHandler(value = {NoClaimFoundException.class})
    public ResponseEntity<Response> handleNoClaimExists(Exception e) {
    	Response response = new ClaimResponse();
    	response.setStatus(createStatus(STATUS_CODE_CLAIM_NOT_FOUND, STATUS_MESSAGE_CLAIM_NOT_FOUND));
    	log.error(e.getMessage(), e);
        return ResponseEntity.ok(response);
    }
    
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleConstraint(MethodArgumentNotValidException e) {
    	Response response = new ClaimResponse();
    	response.setStatus(createStatus(STATUS_CODE_CONSTRAINT_ERROR, STATUS_MESSAGE_CONSTRAINT_ERROR));
    	addFieldErrorsToResponse(response, e);
        return ResponseEntity.ok(response);
    }
    
    private void addFieldErrorsToResponse(Response res, MethodArgumentNotValidException e) {
    	for (FieldError v: e.getFieldErrors()) {
    		res.addMessage(v.getDefaultMessage());
    	}
    }
    
    private Status createStatus(int code, String message) {
		Status status = new Status();
		status.setStatusCode(code);
		status.setStatusMessage(message);
		return status;
	}
}