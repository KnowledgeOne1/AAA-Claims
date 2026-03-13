package com.aaa.service.claim.dao;

import java.util.List;

import com.aaa.service.claim.entity.Claim;
import com.aaa.service.claim.exception.NoClaimFoundException;


public interface ClaimDao {

	public Claim getClaim(String id) throws IllegalArgumentException, NoClaimFoundException;
	public List<Claim> getClaims(String id) throws IllegalArgumentException, NoClaimFoundException;
	public Claim createClaim(Claim claim) throws IllegalArgumentException, NoClaimFoundException;
	public Claim updateClaimStatus(String id, int value) throws IllegalArgumentException, NoClaimFoundException;
	public Claim updateClaimFlag(String id, boolean value) throws IllegalArgumentException, NoClaimFoundException;
	public List<Claim> fraudCheckDuplicate() throws IllegalArgumentException;
}
