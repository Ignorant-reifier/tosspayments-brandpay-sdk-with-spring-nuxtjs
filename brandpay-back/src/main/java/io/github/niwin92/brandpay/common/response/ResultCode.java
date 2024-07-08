package io.github.niwin92.brandpay.common.response;

public enum ResultCode {
    INFO200("INFO-200"),
    ERROR400("ERROR-400"),
    ERROR401("ERROR-401"),
    ERROR403("ERROR-403"),
    ERROR500("ERROR-500");

    private final String text;
    ResultCode(String code) {
        this.text = code;
    }
    public String getText(){
        return text;
    }
}