package io.github.niwin92.brandpay.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String prdId;
    private String prdName;
    private int prdPrice;
    private String prdDesc;
    private String prdImage;

}
