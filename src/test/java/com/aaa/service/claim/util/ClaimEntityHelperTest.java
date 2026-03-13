package com.aaa.service.claim.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aaa.service.claim.entity.Claim;
import com.aaa.service.claim.entity.Document;

@ExtendWith(MockitoExtension.class)
class ClaimEntityHelperTest {

    @Mock
    private DocumentEntityHelper documentEntityHelper;

    @InjectMocks
    private ClaimEntityHelper claimEntityHelper;

    @Test
    @DisplayName("setClaimId should update claim ID when claim is not null")
    void setClaimId_shouldUpdateValue_whenClaimIsPresent() {
        // Given
        Claim claim = new Claim();
        String testId = "CLAIM-123";

        // When
        claimEntityHelper.setClaimId(claim, testId);

        // Then - Assuming Claim has a getClaimId or we verify the setter was called
        // If Claim is a simple POJO, we just check the value
        assert claim.getClaimId().equals(testId);
    }

    @Test
    @DisplayName("setClaimId should do nothing when claim is null")
    void setClaimId_shouldDoNothing_whenClaimIsNull() {
        // When/Then (No exception expected)
        claimEntityHelper.setClaimId(null, "some-id");
    }

    @Test
    @DisplayName("setAllNewDocumentIds should process all documents in the claim")
    void setAllNewDocumentIds_shouldProcessDocuments() {
        // Given
    	Set<Document> documents = new HashSet<>();
        Document doc1 = new Document();
        Document doc2 = new Document();
        documents.add(doc1);
        documents.add(doc2);
        
        Claim claim = new Claim();
        claim.setDocuments(documents);

        String mockUuid = "mocked-uuid-000";

        // Mockito 3.4+ / 5.x static mocking
        try (MockedStatic<Utils> mockedUtils = mockStatic(Utils.class)) {
            mockedUtils.when(Utils::generateUUIDString).thenReturn(mockUuid);

            // When
            claimEntityHelper.setAllNewDocumentIds(claim);

            // Then
            verify(documentEntityHelper, times(2)).setDocumentId(any(Document.class), eq(mockUuid));
            verify(documentEntityHelper, times(2)).setDocumentName(any(Document.class), eq(mockUuid + ".pdf"));
            verify(documentEntityHelper, times(2)).setDateAddedToNow(any(Document.class));
        }
    }

    @Test
    @DisplayName("setAllNewDocumentIds should skip processing if claim or documents are null")
    void setAllNewDocumentIds_shouldHandleNulls() {
        // Case 1: Null Claim
        claimEntityHelper.setAllNewDocumentIds(null);
        verify(documentEntityHelper, never()).setDocumentId(any(), any());

        // Case 2: Claim with null documents list
        Claim claim = new Claim();
        claim.setDocuments(null);
        claimEntityHelper.setAllNewDocumentIds(claim);
        verify(documentEntityHelper, never()).setDocumentId(any(), any());
    }
}
