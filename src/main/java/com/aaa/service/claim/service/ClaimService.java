package com.aaa.service.claim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.aaa.service.claim.aop.Metrics;
import com.aaa.service.claim.dao.ClaimDao;
import com.aaa.service.claim.exception.NoClaimFoundException;
import com.aaa.service.claim.map.ModelMap;
import com.aaa.service.claim.model.Claim;
import com.aaa.service.claim.request.CreateClaimRequest;
import com.aaa.service.claim.request.UpdateClaimRequest;
import com.aaa.service.claim.request.UpdateFlagRequest;
import com.aaa.service.claim.util.ClaimModelHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimService {

	private final ClaimDao claimDao;
	private final ModelMap modalMap;
	private final ClaimModelHelper claimModelHelper;

	@Metrics
	public Claim getClaim(String id) throws IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return (Claim) modalMap.map(claimDao.getClaim(id), Claim.class);
	}

	@Metrics
	public List<Claim> getClaims(String claimId)
			throws IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return (List<Claim>) modalMap.mapList(claimDao.getClaims(claimId), Claim.class);
	}

	@Metrics
	public Claim createClaim(CreateClaimRequest request)
			throws IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		com.aaa.service.claim.entity.Claim entityClaim = (com.aaa.service.claim.entity.Claim) modalMap
				.map(request.getClaim(), com.aaa.service.claim.entity.Claim.class);
		return (Claim) modalMap.map(claimDao.createClaim(entityClaim), Claim.class);
	}

	@Metrics
	public Claim updateClaimStatus(UpdateClaimRequest request)
			throws IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return (Claim) modalMap.map(
				claimDao.updateClaimStatus(claimModelHelper.getUpdateRequestClaimId(request), request.getValue()),
				Claim.class);
	}

	@Metrics
	public Claim updateClaimFlag(UpdateFlagRequest request)
			throws IllegalArgumentException, NoClaimFoundException, JsonProcessingException {
		return (Claim) modalMap.map(
				claimDao.updateClaimFlag(claimModelHelper.getFlagRequestClaimId(request), request.getValue()),
				Claim.class);
	}
}
