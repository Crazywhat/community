package com.jockey.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NO_EXIST(2001,"问题离家出走了！别慌，饿了就会回来的吧..."),
    COMMENT_IS_EMPTY(2002,"评论内容为空"),
    NO_LOGIN(2003,"未登录"),
    COMMENT_NO_TYPE(2004,"评论的类型不能为空"),
    COMMENT_WRONG_TYPE(2005,"错误评论类型"),
    COMMENT_NO_PARENT_ID(2006,"评论的问题或评论 不存在"),
    NOTIFICATION_NO_EXIST(2007,"通知不存在"),
    FILE_UPLOAD_FAIL(2008,"文件上传失败");



    private Integer code;
    private String codeMessage;

    CustomizeErrorCode(Integer code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }

    @Override
    public String getCodeMessage() {
        return codeMessage;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
