package com.aaa.service.claim.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.aaa.service.claim.request.UpdateClaimRequest;
import com.aaa.service.claim.request.UpdateFlagRequest;

class ClaimModelHelperTest {

    private final ClaimModelHelper helper = new ClaimModelHelper();

    @Nested
    @DisplayName("getUpdateRequestClaimId Tests")
    class UpdateRequestTests {

        @Test
        @DisplayName("should return claimId when request and ID are present")
        void shouldReturnId_whenRequestIsValid() {
            UpdateClaimRequest request = new UpdateClaimRequest();
            request.setClaimId("CLAIM-123");

            String result = helper.getUpdateRequestClaimId(request);

            assertThat(result).isEqualTo("CLAIM-123");
        }

        @Test
        @DisplayName("should return null when request is null")
        void shouldReturnNull_whenRequestIsNull() {
            assertThat(helper.getUpdateRequestClaimId(null)).isNull();
        }

        @Test
        @DisplayName("should return null when claimId inside request is null")
        void shouldReturnNull_whenIdIsNull() {
            UpdateClaimRequest request = new UpdateClaimRequest();
            request.setClaimId(null);

            assertThat(helper.getUpdateRequestClaimId(request)).isNull();
        }
    }

    @Nested
    @DisplayName("getFlagRequestClaimId Tests")
    class FlagRequestTests {

        @Test
        @DisplayName("should return claimId when request and ID are present")
        void shouldReturnId_whenRequestIsValid() {
            UpdateFlagRequest request = new UpdateFlagRequest();
            request.setClaimId("FLAG-456");

            String result = helper.getFlagRequestClaimId(request);

            assertThat(result).isEqualTo("FLAG-456");
        }

        @Test
        @DisplayName("should return null when request is null")
        void shouldReturnNull_whenRequestIsNull() {
            assertThat(helper.getFlagRequestClaimId(null)).isNull();
        }
    }
}
