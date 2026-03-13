package com.aaa.service.claim.request;

import jakarta.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.aaa.service.claim.model.Claim;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Data
@NoArgsConstructor
public class CreateClaimRequest {

	@Valid
	private Claim claim;
	

}
