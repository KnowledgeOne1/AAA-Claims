package com.aaa.service.claim.rules;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.aaa.service.claim.dao.ClaimDao;
import com.aaa.service.claim.entity.Claim;
import com.aaa.service.claim.exception.NoClaimFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class FraudDuplicateRule implements Rule {

	private final ClaimDao claimDao;

	public void execute() {
		checkTooManyClaims();
	}

	private void checkTooManyClaims() {
		log.info("Fraud Check: Begin check for too many claims: ");
		List<Claim> claims = (List<Claim>) claimDao.fraudCheckDuplicate();
		if (claims == null || claims.isEmpty()) return;
		for (Claim claim: claims) {
			log.info("Fraud Alert - fraud flag updated for: " + claim.getClaimId());
		}
		updateClaimFlag(claims);
	}

	private void updateClaimFlag(List<Claim> claims) {
		Optional.ofNullable(claims).ifPresent(v -> v.stream().forEach(s -> {
			try {
				claimDao.updateClaimFlag(s.getClaimId(), true);
			} catch (IllegalArgumentException | NoClaimFoundException e) {
				throw new IllegalArgumentException();
			}
		}));
	}
}
