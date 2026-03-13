package com.aaa.service.claim.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.aaa.service.claim.entity.Claim;
import com.aaa.service.claim.entity.Document;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClaimEntityHelper {

	private final DocumentEntityHelper documentEntityHelper;

	public void setClaimId(Claim claim, String value) {
		Optional.ofNullable(claim).ifPresent(v -> v.setClaimId(value));
	}

	public void setAllNewDocumentIds(Claim claim) {
		Optional.ofNullable(claim).map(v -> v.getDocuments()).ifPresent(
				v -> v.forEach(document -> setDocuments(document)));
	}
	
	private void setDocuments(Document document) {
		String uuid = Utils.generateUUIDString();
		documentEntityHelper.setDocumentId(document, uuid);
		documentEntityHelper.setDocumentName(document, String.format("%s.pdf", uuid));
		documentEntityHelper.setDateAddedToNow(document);
	}
}
