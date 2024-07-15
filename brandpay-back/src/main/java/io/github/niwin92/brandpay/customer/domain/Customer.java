package io.github.niwin92.brandpay.customer.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(unique = true, nullable = false)
    private String customerKey;
    private String customerName;
    private String customerEmail;

}


