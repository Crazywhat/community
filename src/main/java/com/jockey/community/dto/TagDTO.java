package com.jockey.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String tagCatalog;
    private List<String> tags;
}
