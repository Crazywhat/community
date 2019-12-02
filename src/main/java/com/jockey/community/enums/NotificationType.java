package com.jockey.community.enums;

public enum NotificationType {

    QUESTION_COMMENT(1,"回复了问题"),
    COMMENT_COMMENT(2,"回复了评论");

    private Integer type;
    private String info;

    NotificationType(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public Integer getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}
