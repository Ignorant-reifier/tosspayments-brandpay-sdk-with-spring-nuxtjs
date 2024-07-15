package io.github.niwin92.brandpay.product.controller;

import io.github.niwin92.brandpay.product.domain.Product;
import io.github.niwin92.brandpay.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product-list")
    public ResponseEntity<List<Product>> productList() {

        List<Product> productList = productService.findAll();

        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/{prdId}")
    public ResponseEntity<Product> productDetail(@PathVariable String prdId) {

        Product product = productService.findById(prdId);

        return ResponseEntity.ok().body(product);
    }

}
