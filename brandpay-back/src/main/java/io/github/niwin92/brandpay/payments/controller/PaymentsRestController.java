package io.github.niwin92.brandpay.payments.controller;

import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.payments.controller.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentsRestController {

    @Autowired
    private PaymentService paymentService;

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

}