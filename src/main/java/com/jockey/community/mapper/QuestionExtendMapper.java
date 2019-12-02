package com.jockey.community.mapper;

import com.jockey.community.dto.QuestionSearchDTO;
import com.jockey.community.model.Question;
import com.jockey.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtendMapper {

    int increaseViewCount(Question record);
    int increaseCommentCount(Question record);

    int countBySearch(String search);

    List<Question> selectByRelatedTag(Question record);

    List<Question> selectBySearch(QuestionSearchDTO record);
}