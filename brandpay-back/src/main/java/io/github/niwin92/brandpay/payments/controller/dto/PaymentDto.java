package io.github.niwin92.brandpay.payments.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class PaymentDto {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "PaymentDto.Request")
    public static class Request {
        @JsonProperty("CODE")
        @ApiModelProperty(value = "code", example = "1", position = 1, required = true)
        String code;

        @JsonProperty("CUSTOMER_KEY")
        @ApiModelProperty(value = "customerKey", example = "1", position = 3, required = true)
        String customerKey;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "PaymentDto.Response")
    public static class Response {
        @JsonProperty("USR_SEQ")
        @ApiModelProperty(value = "usrSeq", example = "1", position = 1, required = true)
        String usrSeq;

        @JsonProperty("CALL_SEQ")
        @ApiModelProperty(value = "callSeq", example = "1", position = 3, required = true)
        String callSeq;
    }

}
