package com.jockey.community.dto;

import lombok.Data;

@Data
public class QuestionSearchDTO {
    String search;
    Integer offset;
    Integer size;
}
