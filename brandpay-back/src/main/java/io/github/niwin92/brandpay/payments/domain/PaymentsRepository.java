package io.github.niwin92.brandpay.payments.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentsRepository extends JpaRepository<Payments, String>, JpaSpecificationExecutor<Payments> {
}
