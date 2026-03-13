package com.aaa.service.claim.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aaa.service.claim.exception.AccessDeniedException;
import com.aaa.service.claim.exception.NoClaimFoundException;
import com.aaa.service.claim.request.CreateClaimRequest;
import com.aaa.service.claim.request.UpdateClaimRequest;
import com.aaa.service.claim.request.UpdateFlagRequest;
import com.aaa.service.claim.service.ClaimService;
import com.aaa.service.claim.response.ClaimResponse;
import com.aaa.service.claim.response.ClaimResponseList;
import com.aaa.service.claim.response.Response;
import com.aaa.service.claim.util.ResponseUtil;
import com.google.firebase.auth.FirebaseAuthException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class ClaimController {

	private final ClaimService service;
	

	@Operation(summary = "Retrieves a claim by claim id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Claim Retrieved"),
			@ApiResponse(responseCode = "400", description = "Request bad"), @ApiResponse(responseCode = "500", description = "Error") })
	@GetMapping(value = "/getClaim/{id}")
	public @ResponseBody ResponseEntity<Response> getClaim(@PathVariable("id") String id)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoClaimFoundException, JsonProcessingException, FirebaseAuthException, AccessDeniedException {
		return ResponseEntity.ok(ResponseUtil.mapResponse(ClaimResponse.class, service.getClaim(id)));
	}
	
	@Operation(summary = "Retrieves a list of claims by claim id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Claims Retrieved"),
			@ApiResponse(responseCode = "400", description = "Request bad"), @ApiResponse(responseCode = "500", description = "Error") })
	@GetMapping(value = "/getClaims/{id}")
	public @ResponseBody ResponseEntity<?> getClaims(@PathVariable("id") String id)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoClaimFoundException, JsonProcessingException, FirebaseAuthException, AccessDeniedException {
		return ResponseEntity.ok(ResponseUtil.mapResponseList(ClaimResponseList.class, service.getClaims(id)));
	}

	@Operation(summary = "Creates a new claim")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Claim Created"),
			@ApiResponse(responseCode = "400", description = "Request bad"), @ApiResponse(responseCode = "500", description = "Error"), })
	@RequestMapping(value = "/createClaim", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Response> createClaim(@Valid @RequestBody CreateClaimRequest request)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return ResponseEntity.ok(ResponseUtil.mapResponse(ClaimResponse.class, service.createClaim(request)));
	}
	
	@Operation(summary = "Changes claim status")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Claim Created"),
			@ApiResponse(responseCode = "400", description = "Request bad"), @ApiResponse(responseCode = "500", description = "Error"), })
	@RequestMapping(value = "/changeClaimStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Response> changeClaimStatus(@Valid @RequestBody UpdateClaimRequest request)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return ResponseEntity.ok(ResponseUtil.mapResponse(ClaimResponse.class, service.updateClaimStatus(request)));
	}
	
	@Operation(summary = "Changes claim flag")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Claim Created"),
			@ApiResponse(responseCode = "400", description = "Request bad"), @ApiResponse(responseCode = "500", description = "Error"), })
	@RequestMapping(value = "/changeClaimFlag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Response> changeClaimFlag(@Valid @RequestBody UpdateFlagRequest request)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return ResponseEntity.ok(ResponseUtil.mapResponse(ClaimResponse.class, service.updateClaimFlag(request)));
	}
}
