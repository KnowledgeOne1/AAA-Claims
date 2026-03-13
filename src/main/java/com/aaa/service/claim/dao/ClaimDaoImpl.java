package com.aaa.service.claim.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aaa.service.claim.entity.Claim;
import com.aaa.service.claim.exception.NoClaimFoundException;
import com.aaa.service.claim.util.ClaimEntityHelper;
import com.aaa.service.claim.util.DocumentEntityHelper;
import com.aaa.service.claim.util.EntityUtil;
import com.aaa.service.claim.util.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClaimDaoImpl implements ClaimDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	private final ClaimEntityHelper claimEntityHelper;
	private final DocumentEntityHelper documentEntityHelper;
	
	
	@Transactional
	public Claim getClaim(String claimId) throws IllegalArgumentException, NoClaimFoundException {
		return findClaimById(claimId);
	}
	
	@Transactional
	public List<Claim> getClaims(String userId) throws IllegalArgumentException, NoClaimFoundException {
		return findClaimsByUserId(userId);
	}
	
	@Transactional
	public Claim createClaim(Claim claim) throws IllegalArgumentException, NoClaimFoundException {
		claimEntityHelper.setClaimId(claim, Utils.generateUUIDString());
		claimEntityHelper.setAllNewDocumentIds(claim);
		Optional.ofNullable(claim)
			.map(Claim::getDocuments)
			.ifPresent(s -> s.forEach(v -> v.setClaim(claim)));
		EntityUtil.saveEntity(entityManager, claim);
		return claim;
	}
	
	@Transactional
	public Claim updateClaimStatus(String claimId, int value) throws IllegalArgumentException, NoClaimFoundException {
		Claim claim = findClaimById(claimId);
		claim.setStatus(value);
		EntityUtil.saveEntity(entityManager, claim);
		return claim;
	}
	
	@Transactional
	public Claim updateClaimFlag(String claimId, boolean value) throws IllegalArgumentException, NoClaimFoundException {
		Claim claim = findClaimById(claimId);
		claim.setFlag(value);
		EntityUtil.saveEntity(entityManager, claim);
		return claim;
	}
	
	@Transactional
	public List<Claim> fraudCheckDuplicate() throws IllegalArgumentException {
		try {
			return (List<Claim>) entityManager.createNamedQuery("findDuplicateClaims").getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}
	
	private Claim findClaimById(String claimId) throws NoClaimFoundException {
		try {
			return (Claim) entityManager.createNamedQuery("findClaimById").setParameter("id", claimId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoClaimFoundException("Not Found");
		}
	}
	
	private List<Claim> findClaimsByUserId(String userId) throws NoClaimFoundException {
		try {
			return (List<Claim>) entityManager.createNamedQuery("findClaimsByUserId").setParameter("userId", userId).getResultList();
		} catch (NoResultException e) {
			throw new NoClaimFoundException("Not Found");
		}
	}
}
