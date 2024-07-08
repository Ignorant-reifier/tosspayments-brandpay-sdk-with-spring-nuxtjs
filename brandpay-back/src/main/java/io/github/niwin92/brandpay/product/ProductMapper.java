package io.github.niwin92.brandpay.product;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDto> findAll();

    ProductDto findById(String prdId);

}
