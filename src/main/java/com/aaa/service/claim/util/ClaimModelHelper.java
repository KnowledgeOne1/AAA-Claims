package com.aaa.service.claim.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.aaa.service.claim.model.Claim;
import com.aaa.service.claim.request.CreateClaimRequest;
import com.aaa.service.claim.request.UpdateClaimRequest;
import com.aaa.service.claim.request.UpdateFlagRequest;

@Component
public class ClaimModelHelper {

	public String getUpdateRequestClaimId(UpdateClaimRequest request) {
		return Optional.ofNullable(request)
		.map(UpdateClaimRequest::getClaimId)
		.orElse(null);
	}
	
	public String getFlagRequestClaimId(UpdateFlagRequest request) {
		return Optional.ofNullable(request)
		.map(UpdateFlagRequest::getClaimId)
		.orElse(null);
	}
}
