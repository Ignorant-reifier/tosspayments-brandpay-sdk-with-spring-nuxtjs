package io.github.niwin92.brandpay.payments.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

public class Payment {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "Payment.Payments")
    public static class Payments {
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
        private Object card;
        private Object virtualAccount;
        private Transfer transfer;
        private Object mobilePhone;
        private Object giftCertificate;
        private CashReceipt cashReceipt;
        private Object cashReceipts;
        private Object discount;
        private Object cancels;
        private String secret;
        private String type;
        private Object easyPay;
        private String country;
        private Object failure;
        private boolean isPartialCancelable;
        private Receipt receipt;
        private Checkout checkout;
        private String currency;
        private int totalAmount;
        private int balanceAmount;
        private int suppliedAmount;
        private int vat;
        private int taxFreeAmount;
        private String method;
        private String version;
        // getters and setters
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "Payment.Transfer")
    public static class Transfer {
        private String bankCode;
        private String settlementStatus;
        // getters and setters
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "Payment.CashReceipt")
    public static class CashReceipt {
        private String type;
        private String receiptKey;
        private String issueNumber;
        private String receiptUrl;
        private int amount;
        private int taxFreeAmount;
        // getters and setters
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "Payment.Receipt")
    public static class Receipt {
        private String url;
        // getters and setters
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "Payment.Checkout")
    public static class Checkout {
        private String url;
        // getters and setters
    }
}


