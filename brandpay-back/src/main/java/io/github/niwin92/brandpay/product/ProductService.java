package io.github.niwin92.brandpay.product;

import io.github.niwin92.brandpay.product.domain.Product;
import io.github.niwin92.brandpay.product.domain.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private ModelMapper modelMapper;

    public List<ProductDto> findAll() {
        List<Product> result = productRepository.findAll();
        List<ProductDto> content = result.stream().map(this::toResponse).collect(Collectors.toList());

        return content;
    }

    public ProductDto findById(String prdId) {
        Optional<Product> result = productRepository.findById(prdId);
        ProductDto content = result.map(this::toResponse).get();
        return content;
    }

    private ProductDto toResponse(Product entity) {
        return modelMapper.map(entity, ProductDto.class);
    }

}
