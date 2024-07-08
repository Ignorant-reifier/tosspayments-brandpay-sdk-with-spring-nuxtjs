package io.github.niwin92.brandpay.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.CaseFormat;
import io.github.niwin92.brandpay.common.dto.OriginConvertDto;
import io.github.niwin92.brandpay.common.dto.OriginDto;
import io.github.niwin92.brandpay.common.dto.OriginNamingDto;
import io.github.niwin92.brandpay.common.dto.OriginUnderDto;
import io.github.niwin92.brandpay.common.utility.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ModelMapper modelMapperCamelToUnder;

    @PostMapping("/v1/confirm")
    public ResponseEntity dtoToMap1(@RequestBody OriginDto dto){
        List<Map<String,Object>> dtoToMapList = new ArrayList<Map<String,Object>>();
        return ResponseEntity.ok(dtoToMapList);
    }

    @RequestMapping("/callback-auth")
    public String callbackAuth(HttpServletRequest request, HttpServletResponse response){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("request  : " + request);
        System.out.println("response : " + response);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "";
    }



}
