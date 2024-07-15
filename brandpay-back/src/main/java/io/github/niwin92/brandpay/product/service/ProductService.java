package io.github.niwin92.brandpay.product.service;

import io.github.niwin92.brandpay.product.domain.Product;
import io.github.niwin92.brandpay.product.domain.ProductRepository;
import io.github.niwin92.brandpay.product.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper  productMapper;

    public List<Product> findAll() {
//        List<Product> list = productMapper.findAll();
        List<Product> list = productRepository.findAll();
        return list;
    }

    public Product findById(String prdId) {
//        Product result = productMapper.findById(prdId);
        Product result = productRepository.findById(prdId).get();
        return result;
    }
}
