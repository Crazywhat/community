package com.jockey.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTON_NO_EXIST("问题离家出走了！别慌，饿了就会回来的吧...");

    private String codeMessage;

    CustomizeErrorCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }

    @Override
    public String getCodeMessage() {
        return codeMessage;
    }

}
