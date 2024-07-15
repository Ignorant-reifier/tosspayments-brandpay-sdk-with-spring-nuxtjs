package io.github.niwin92.brandpay.customer.domain;

import io.github.niwin92.brandpay.payments.domain.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
}

