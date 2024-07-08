package io.github.niwin92.brandpay.common.exception;

public class CustomServerException extends RuntimeException{

    public CustomServerException(String message) {
        super(message);
    }

    public CustomServerException(Exception e) {
        super(e.getMessage());
    }
}
