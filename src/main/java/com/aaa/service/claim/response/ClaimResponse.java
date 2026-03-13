package com.aaa.service.claim.response;

import java.util.ArrayList;
import java.util.List;

import com.aaa.service.claim.model.Claim;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClaimResponse implements Response {
	public Claim claim;
	public Status status;
	public List<String> messages;
	public void addMessage(String message) {
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
	}
}
