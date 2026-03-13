package com.aaa.service.claim.response;

import java.util.List;

import com.aaa.service.claim.model.Claim;

public interface ResponseList {
	public List<Claim> getClaims();
	public void setClaims(List<Claim> claims);
	public Status getStatus();
	public void setStatus(Status status);
	public List<String> getMessages();
	public void setMessages(List<String> messages);
	public void addMessage(String message);
}
