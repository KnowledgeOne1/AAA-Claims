package com.aaa.service.claim.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateFlagRequest {

	@NotBlank(message = "Claim id is required")
	@Size(max = 100, message = "Claim id too long")
	private String claimId;
	
	@NotNull
	private Boolean value;

}
