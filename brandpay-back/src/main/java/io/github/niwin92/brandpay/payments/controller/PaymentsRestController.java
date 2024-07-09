package io.github.niwin92.brandpay.payments.controller;

import io.github.niwin92.brandpay.common.dto.OriginDto;
import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.common.response.ResponseData;
import io.github.niwin92.brandpay.payments.controller.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentsRestController {

    private final PaymentService paymentService;

    public PaymentsRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/callback-auth")
    public ResponseEntity<String> callbackAuth(@RequestParam("code") String code,
                                          @RequestParam("customerKey") String customerKey) {
        try {
            PaymentDto.Request requestParam = PaymentDto.Request.builder().code(code).customerKey(customerKey).build();
            return paymentService.accessToken(requestParam);
        } catch (Exception e) {
           throw new CustomServerException(e);
        }

    }

    @PostMapping("/brandpay-confirm")
    public ResponseEntity<String> brandpayConfirm(@RequestBody String jsonBody){

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("jsonBody : " + jsonBody);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        try {
            return paymentService.brandpayConfirm(jsonBody);
        } catch (Exception e) {
            throw new CustomServerException(e);
        }

    }

    @RequestMapping(value = "/callback-success")
    public String callbackSuccess(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "customerKey") String customerKey) {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("customerKey : " + customerKey);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "";
    }

    @RequestMapping(value = "/callback-fail")
    public String callbackFail(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

}