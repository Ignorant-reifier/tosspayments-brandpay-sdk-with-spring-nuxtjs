package io.github.niwin92.brandpay.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Result {

    @JsonProperty("code")
    @ApiModelProperty(value = "code", example = "", position = 1, required = true)
    String code;

    @JsonProperty("message")
    @ApiModelProperty(value = "message", example = "", position = 2, required = true)
    String message;

}

