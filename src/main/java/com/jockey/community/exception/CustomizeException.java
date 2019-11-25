package com.jockey.community.exception;

public class CustomizeException extends RuntimeException {
    Integer errorCode;

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        super(iCustomizeErrorCode.getCodeMessage());
        this.errorCode = iCustomizeErrorCode.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
