package com.jockey.community.mapper;

import com.jockey.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface QuestionMapper {


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO question (title,description,tag,gmt_create,gmt_modified,creator) VALUES(#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void insertQuestion(Question question);

}
