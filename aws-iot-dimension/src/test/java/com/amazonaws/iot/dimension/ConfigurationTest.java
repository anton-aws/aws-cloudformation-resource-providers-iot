package com.amazonaws.iot.dimension;

import static com.amazonaws.iot.dimension.TestConstants.DIMENSION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigurationTest {

    private Configuration configuration;

    @BeforeEach
    public void setup() {
        configuration = new Configuration();
    }

    @Test
    public void resourceDefinedTags_ModelWithTags_VerifyTranslation() {

        Set<Tag> modelTags = ImmutableSet.of(
                Tag.builder()
                        .key("resourceTagKey")
                        .value("resourceTagValue")
                        .build(),
                Tag.builder()
                        .key("resourceTagKey2")
                        .value("resourceTagValue2")
                        .build());
        ResourceModel model = ResourceModel.builder()
                .name(DIMENSION_NAME)
                .tags(modelTags)
                .build();

        Map<String, String> result = configuration.resourceDefinedTags(model);

        assertThat(result).isEqualTo(ImmutableMap.of(
                "resourceTagKey", "resourceTagValue",
                "resourceTagKey2", "resourceTagValue2"));
    }
}
