package io.github.niwin92.brandpay.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDto> findAll() {
        return productMapper.findAll();
    }

    public ProductDto findById(String prdId) {
        return productMapper.findById(prdId);
    }

}
