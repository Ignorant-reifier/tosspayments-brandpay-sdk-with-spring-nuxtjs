package io.github.niwin92.brandpay.payments.controller;

import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.common.response.ResponseData;
import io.github.niwin92.brandpay.common.response.Result;
import io.github.niwin92.brandpay.common.utility.CommonUtils;
import io.github.niwin92.brandpay.payments.controller.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    public String brandUrl;

    @Value("${api.secretKey}")
    public String secretKey;

    public String accessTokeUrl = "";

    public ResponseData<String> accessToken(PaymentDto.Request dto) throws Exception {
        accessTokeUrl = brandUrl + "/v1/brandpay/authorizations/access-token";
        ResponseData<String> responseData = new ResponseData<>();
        try {
            String code = dto.getCode();
            String customerKey = dto.getCustomerKey();

            secretKey = secretKey + ":";
            String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", String.format("Basic %s", encodedSecretKey));
            headers.set("Content-Type", "application/json");

            String body = String.format("{\"grantType\":\"AuthorizationCode\",\"customerKey\":\"%s\",\"code\":\"%s\"}", customerKey, code);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(accessTokeUrl, HttpMethod.POST, entity, String.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                log.info(response.getBody());
                responseData.setList(response.getBody());
                responseData.setResult(Result.builder().code("200").message("성공").build());
            } else {
                responseData.setResult(Result.builder().code("500").message(response.getBody()).build());
            }
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw new CustomServerException(ex);

        }
        return responseData;
    }

}
