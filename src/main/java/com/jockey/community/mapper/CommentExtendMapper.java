package com.jockey.community.mapper;

import com.jockey.community.model.Comment;

public interface CommentExtendMapper {

    int increaseCommentCount(Comment record);
}