package com.aaa.service.claim.scheduledTask;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aaa.service.claim.dao.ClaimDao;
import com.aaa.service.claim.rules.FraudDuplicateRule;
import com.aaa.service.claim.rules.Rule;
import com.aaa.service.claim.rules.ValidateRules;

@RequiredArgsConstructor
@Component
public class FraudScheduledTask {

	private final ClaimDao claimDao;
	private final ValidateRules validateRules;
	private List<Rule> rules = new ArrayList<>();
	
	
	@Scheduled(fixedRate = 3600000) // Check every hour
	public void checkFraud()  {
		rules.add(new FraudDuplicateRule(claimDao));
		validateRules.validate(rules);
	}
	
}
