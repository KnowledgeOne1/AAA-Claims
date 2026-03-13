package com.aaa.service.claim.request;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateClaimRequest {

	@NotBlank(message = "Claim id is required")
	@Size(max = 100, message = "Claim id too long")
	private String claimId;
	
	@Range(min=1, max=3, message = "Value must be 1, 2, or 3")
	private Integer value;

}
