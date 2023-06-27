package com.challenge.midas.exception;

public class ShippingAddressException extends Exception {
    private static final long serialVersionUID = 1L;

    public ShippingAddressException(String message) {
        super(message);
    }
}