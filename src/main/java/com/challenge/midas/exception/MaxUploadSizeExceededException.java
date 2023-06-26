package com.challenge.midas.exception;

public class MaxUploadSizeExceededException extends Exception {

    private static final long serialVersionUID = 1L;

    public MaxUploadSizeExceededException(String message) {
        super(message);
    }
}