package com.jockey.community.model;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String avatarUrl;
    private String accountId;
    private String name;
    private String bio;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;


}
