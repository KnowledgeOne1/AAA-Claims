package com.aaa.service.claim.map;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

class ModelMapTest {

    private ModelMap<Object> modelMap;

    @BeforeEach
    void setUp() {
        modelMap = new ModelMap<>();
    }

    @Test
    @DisplayName("mapList should convert a List-compatible object into a List of the target class")
    void mapList_shouldConvertToList() throws JsonProcessingException {
        // Given
        List<TestBean> sourceList = List.of(
            new TestBean("User A", 30),
            new TestBean("User B", 40)
        );

        // When
        List<?> result = modelMap.mapList(sourceList, (Class<Object>) (Object) TestBean.class);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isExactlyInstanceOf(TestBean.class);
        assertThat(((TestBean) result.get(1)).getName()).isEqualTo("User B");
    }

    // Static inner classes for testing
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestBean {
        private String name;
        private int age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ExtendedBean {
        private String name;
        private int age;
        private String extraInfo;
    }
}
