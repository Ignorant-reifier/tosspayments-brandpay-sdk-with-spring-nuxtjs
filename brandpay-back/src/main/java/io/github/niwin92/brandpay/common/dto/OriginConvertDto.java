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
public class OriginConvertDto {

    @JsonProperty("testCode")
    private String TEST_CODE;

    @JsonProperty("testName")
    private String TEST_NAME;

    @JsonProperty("testDescription")
    private String TEST_DESCRIPTION;
}
