package com.jockey.community.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private int creator;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
}
