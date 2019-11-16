package com.jockey.community.service;

import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.mapper.QuestionMapper;
import com.jockey.community.mapper.UserMapper;
import com.jockey.community.model.Question;
import com.jockey.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;


    public Question addQuestion(Question question){
        questionMapper.insertQuestion(question);
        return question;
    }


    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.selectAllQuestion();
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question: questions) {
            User user = userMapper.selectUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }
}
