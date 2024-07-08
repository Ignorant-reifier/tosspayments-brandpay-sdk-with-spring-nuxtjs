package io.github.niwin92.brandpay.payments.controller;

import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.common.response.ResponseData;
import io.github.niwin92.brandpay.common.response.Result;
import io.github.niwin92.brandpay.common.response.ResultCode;
import io.github.niwin92.brandpay.common.utility.CommonUtils;
import io.github.niwin92.brandpay.payments.controller.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.rmi.ServerException;
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

    public ResponseData callAuth(PaymentDto.Request dto) throws Exception {
        ResponseData relayResponse = new ResponseData();
        try {
            Map<String,String> map = new HashMap<>();
            map.put("code", dto.getCode());
            map.put("customerKey", dto.getCustomerKey());

            String url = CommonUtils.relayURL(brandUrl + "/api/v1/relay/callAuth", map);

            ResponseEntity<String> result = restTemplate.getForEntity(new URI(url), String.class);
            if(result.getStatusCode().equals(HttpStatus.OK)){
                relayResponse.setResult(Result.builder().code("200").message("성공").build());
            } else {
                throw new CustomServerException(String.valueOf(result.getStatusCode()));
            }
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw ex;
        }
        return relayResponse;
    }

}
