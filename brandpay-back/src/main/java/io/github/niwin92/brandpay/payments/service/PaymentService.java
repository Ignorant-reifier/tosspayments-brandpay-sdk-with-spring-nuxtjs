package io.github.niwin92.brandpay.payments.service;

import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.payments.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


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

    private HttpHeaders getAuthHeaders() {
        String authorization = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Basic %s", authorization));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public ResponseEntity<String> accessToken(PaymentDto.Request dto) throws Exception {

        String apiUrl = brandUrl + "/authorizations/access-token";

        try {
            String code = dto.getCode();
            String customerKey = dto.getCustomerKey();

            // create JSON Object
            JSONObject requestBody = new JSONObject();
            requestBody.put("grantType", "AuthorizationCode");
            requestBody.put("customerKey", customerKey);
            requestBody.put("code", code);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toJSONString(), this.getAuthHeaders());

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                log.info("\n#####################################\n{}\n#####################################", response.getBody());
                // TODO: 발급 성공 비즈니스 로직을 구현

                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            } else {
                // TODO: 발급 실패 비즈니스 로직을 구현

                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw new CustomServerException(ex);
        }
    }

    public ResponseEntity<String> brandpayConfirm(String jsonBody) throws Exception {

        String apiUrl = brandUrl + "/payments/confirm";

        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        String customerKey;

        try {

            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
            paymentKey = (String) requestData.get("paymentKey");
            customerKey = (String) requestData.get("customerKey");

        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new CustomServerException(e);
        }

        // create JSON Object
        JSONObject requestBody = new JSONObject();
        requestBody.put("orderId", orderId);
        requestBody.put("amount", amount);
        requestBody.put("paymentKey", paymentKey);
        requestBody.put("customerKey", customerKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toJSONString(), this.getAuthHeaders());

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        try {

            if(response.getStatusCode().equals(HttpStatus.OK)){
                log.info("\n#####################################\n{}\n#####################################", response.getBody());
                // TODO: 결제 완료 비즈니스 로직을 구현

                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            } else {
                // TODO: 결제 승인 실패 비즈니스 로직을 구현

                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw new CustomServerException(ex);
        }

    }

}
