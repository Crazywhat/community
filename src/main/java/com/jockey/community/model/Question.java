package com.jockey.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private Integer creator;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
