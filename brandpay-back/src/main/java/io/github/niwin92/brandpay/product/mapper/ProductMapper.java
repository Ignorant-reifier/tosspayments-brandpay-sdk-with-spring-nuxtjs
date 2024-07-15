package io.github.niwin92.brandpay.product.mapper;

import io.github.niwin92.brandpay.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();

    Product findById(String prdId);

}
