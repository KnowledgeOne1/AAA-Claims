package com.aaa.service.claim.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    @DisplayName("generateUUIDString should return a valid UUID string")
    void generateUUIDString_shouldReturnValidUUID() {
        // When
        String result = Utils.generateUUIDString();

        // Then
        assertThat(result).isNotNull();
        // Verifies the string can be parsed back into a UUID object without error
        assertThat(UUID.fromString(result)).isNotNull();
        // UUIDs are 36 characters long (32 hex chars + 4 hyphens)
        assertThat(result).hasSize(36);
    }

    @Test
    @DisplayName("generateUUIDString should produce unique values on consecutive calls")
    void generateUUIDString_shouldBeUnique() {
        // When
        String first = Utils.generateUUIDString();
        String second = Utils.generateUUIDString();

        // Then
        assertThat(first).isNotEqualTo(second);
    }
}
