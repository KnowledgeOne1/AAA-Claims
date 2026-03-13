package com.aaa.service.claim.rules;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ValidateRules {
	
	public void validate(List<Rule> rules) {
		for (Rule rule : rules) {
			rule.execute();
		}
	}
}
