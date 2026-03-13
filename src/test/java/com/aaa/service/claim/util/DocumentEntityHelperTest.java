package com.aaa.service.claim.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.aaa.service.claim.entity.Document;

class DocumentEntityHelperTest {

    private final DocumentEntityHelper helper = new DocumentEntityHelper();

    @Test
    @DisplayName("setDocumentId should update value when document is present")
    void setDocumentId_updatesValue() {
        Document doc = new Document();
        helper.setDocumentId(doc, "DOC-123");
        assertThat(doc.getDocumentId()).isEqualTo("DOC-123");
    }

    @Test
    @DisplayName("setDocumentName should update value when document is present")
    void setDocumentName_updatesValue() {
        Document doc = new Document();
        helper.setDocumentName(doc, "test-file.pdf");
        assertThat(doc.getDocumentName()).isEqualTo("test-file.pdf");
    }

    @Test
    @DisplayName("setDateAddedToNow should set current timestamp")
    void setDateAddedToNow_updatesToCurrentTime() {
        Document doc = new Document();
        
        helper.setDateAddedToNow(doc);

        // Verify the date is within 1 second of "now" to account for execution time
        assertThat(doc.getDateAdded()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    @DisplayName("Methods should not throw exception when document is null")
    void helperMethods_shouldHandleNullDocument() {
        // These should execute silently without NullPointerException
        helper.setDocumentId(null, "id");
        helper.setDocumentName(null, "name");
        helper.setDateAddedToNow(null);
    }
}
