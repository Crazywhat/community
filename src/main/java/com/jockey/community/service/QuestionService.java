package com.jockey.community.service;

import com.jockey.community.mapper.QuestionMapper;
import com.jockey.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;


    public Question addQuestion(Question question){
        questionMapper.insertQuestion(question);
        return question;
    }


}
