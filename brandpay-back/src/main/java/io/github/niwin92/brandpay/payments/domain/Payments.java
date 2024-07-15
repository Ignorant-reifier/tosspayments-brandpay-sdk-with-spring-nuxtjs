package io.github.niwin92.brandpay.payments.domain;

import io.github.niwin92.brandpay.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payments")
public class Payments extends BaseEntity {

        @Column(name = "mid")
        private String mId;
        private String lastTransactionKey;
        private String paymentKey;
        private String orderId;
        private String orderName;
        private int taxExemptionAmount;
        private String status;
        private Date requestedAt;
        private Date approvedAt;
        private boolean useEscrow;
        private boolean cultureExpense;
        private String secret;
        private String type;
        private String country;
        private boolean isPartialCancelable;
        private String currency;
        private int totalAmount;
        private int balanceAmount;
        private int suppliedAmount;
        private int vat;
        private int taxFreeAmount;
        private String method;
        private String version;

        private String transferBankCode;
        private String transferSettlementStatus;

        private String cashReceiptType;
        private String cashReceiptReceiptKey;
        private String cashReceiptIssueNumber;
        private String cashReceiptReceiptUrl;
        private int cashReceiptAmount;
        private int cashReceiptTaxFreeAmount;

        private String receiptUrl;
        private String checkoutUrl;
}

