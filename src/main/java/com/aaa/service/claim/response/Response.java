package com.aaa.service.claim.response;

import java.util.List;

import com.aaa.service.claim.model.Claim;

public interface Response {
	public Claim getClaim();
	public void setClaim(Claim user);
	public Status getStatus();
	public void setStatus(Status status);
	public List<String> getMessages();
	public void setMessages(List<String> messages);
	public void addMessage(String message);
}
