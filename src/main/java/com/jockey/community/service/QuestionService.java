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


    public List<QuestionDTO> listByCreator(Integer creator,Integer page, Integer size){
        List<Question> questions = questionMapper.selectCreatorRangeQuestion(creator,(page-1)*size,size);
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


    public List<QuestionDTO> list(Integer page, Integer size) {
        List<Question> questions = questionMapper.selectRangeQuestion((page-1)*size,size);
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

    public Integer getSizeByCreator(Integer creator){ return  questionMapper.getQuestionsCountByCreator(creator);}
    public Integer getSize(){
        return questionMapper.getQuestionsCount();
    }



    public QuestionDTO getQuestionInfoById(Integer id) {

        Question question = questionMapper.getQuestionById(id);
        User user = userMapper.selectUserById(question.getCreator());

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);

        return questionDTO;
    }
}
