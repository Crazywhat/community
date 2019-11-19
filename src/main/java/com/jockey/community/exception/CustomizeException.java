package com.jockey.community.exception;

public class CustomizeException extends RuntimeException {

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        super(iCustomizeErrorCode.getCodeMessage());
    }
}
