package io.github.niwin92.brandpay.customer.controller;


import io.github.niwin92.brandpay.common.exception.CustomServerException;
import io.github.niwin92.brandpay.customer.domain.Customer;
import io.github.niwin92.brandpay.customer.domain.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public ResponseEntity<List<Customer>> customer() {
        List<Customer> result;
        try {
            result =  customerRepository.findAll();
        } catch (Exception e) {
           throw new CustomServerException(e);
        }
        return ResponseEntity.ok().body(result);
    }

}