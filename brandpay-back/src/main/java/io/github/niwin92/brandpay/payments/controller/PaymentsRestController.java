package io.github.niwin92.brandpay.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.CaseFormat;
import io.github.niwin92.brandpay.common.dto.OriginConvertDto;
import io.github.niwin92.brandpay.common.dto.OriginDto;
import io.github.niwin92.brandpay.common.dto.OriginNamingDto;
import io.github.niwin92.brandpay.common.dto.OriginUnderDto;
import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.common.response.ResponseData;
import io.github.niwin92.brandpay.common.utility.StringUtils;
import io.github.niwin92.brandpay.payments.controller.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentsRestController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/v1/confirm")
    public ResponseEntity dtoToMap1(@RequestBody OriginDto dto){
        List<Map<String,Object>> dtoToMapList = new ArrayList<Map<String,Object>>();
        return ResponseEntity.ok(dtoToMapList);
    }

    @GetMapping("/callback-auth")
    public ResponseEntity<ResponseData> callbackAuth(HttpServletRequest request, HttpServletResponse response){
        ResponseData result = null;
        try {
            String code = request.getParameter("code");
            String customerKey = request.getParameter("customerKey");
            PaymentDto.Request requestParam = PaymentDto.Request.builder().code(code).customerKey(customerKey).build();
            result = paymentService.accessToken(requestParam);
        } catch (Exception ex) {
           throw new CustomServerException(ex);
        }
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/callback-success")
    public String callbackSuccess(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "customerKey") String customerKey) {
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.debug("customerKey : " + customerKey);
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "";
    }

    @RequestMapping(value = "/callback-fail")
    public String callbackFail(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

}