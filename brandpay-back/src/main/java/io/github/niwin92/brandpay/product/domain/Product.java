package io.github.niwin92.brandpay.product.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {

    @Id
    @Column(unique = true, nullable = false)
    private String prdId;
    private String prdNm;
    private int prdPrice;
    private String prdDesc;
    private String prdImg;
}
