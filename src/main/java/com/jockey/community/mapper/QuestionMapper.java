package com.jockey.community.mapper;

import com.jockey.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO question (title,description,tag,gmt_create,gmt_modified,creator) VALUES(#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void insertQuestion(Question question);


    @Select("SELECT * FROM question")
    List<Question> selectAllQuestion();

    @Select("SELECT * FROM question LIMIT #{offset},#{size}")
    List<Question> selectRangeQuestion(@Param("offset") Integer offset,@Param("size") Integer size);


    @Select("SELECT COUNT(1) FROM question")
    Integer getQuestionsCount();

    @Select("SELECT COUNT(1) FROM question WHERE creator=#{creator}")
    Integer getQuestionsCountByCreator(@Param("creator")Integer creator);

    @Select("SELECT * FROM question WHERE creator=#{creator} LIMIT #{offset},#{size}")
    List<Question> selectCreatorRangeQuestion(@Param("creator")Integer creator, @Param("offset") Integer offset,@Param("size") Integer size);

    @Select("SELECT * FROM question WHERE id=#{id}")
    Question getQuestionById(@Param("id") Integer id);
}
