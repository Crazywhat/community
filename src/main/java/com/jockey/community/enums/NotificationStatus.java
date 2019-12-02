package com.jockey.community.enums;

public enum NotificationStatus {

    UN_READ(0),
    READ(1);
    private int status;

    NotificationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
