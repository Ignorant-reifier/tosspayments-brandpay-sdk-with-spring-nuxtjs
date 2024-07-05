package io.github.niwin92.brandpay.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OriginUnderDto {

    @JsonProperty("TEST_CODE")
    private String TEST_CODE;

    @JsonProperty("TEST_NAME")
    private String TEST_NAME;

    @JsonProperty("TEST_DESCRIPTION")
    private String TEST_DESCRIPTION;
}
