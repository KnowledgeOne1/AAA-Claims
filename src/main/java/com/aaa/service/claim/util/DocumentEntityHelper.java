package com.aaa.service.claim.util;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;
import com.aaa.service.claim.entity.Document;


@Component
public class DocumentEntityHelper {

	public void setDocumentId(Document document, String value) {
		Optional.ofNullable(document)
		.ifPresent(v -> v.setDocumentId(value));
	}
	
	public void setDocumentName(Document document, String value) {
		Optional.ofNullable(document)
		.ifPresent(v -> v.setDocumentName(value));
	}
	
	public void setDateAddedToNow(Document document) {
		Optional.ofNullable(document)
		.ifPresent(v -> v.setDateAdded(LocalDateTime.now()));
	}
}
