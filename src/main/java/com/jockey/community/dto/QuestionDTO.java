package com.jockey.community.dto;

import com.jockey.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;
}
