package com.jockey.community.service;

import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.mapper.QuestionMapper;
import com.jockey.community.mapper.UserMapper;
import com.jockey.community.model.Question;
import com.jockey.community.model.QuestionExample;
import com.jockey.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
        questionMapper.insertSelective(question);
        return question;
    }


    public List<QuestionDTO> listByCreator(Integer creator,Integer page, Integer size){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds((page - 1) * size, size));

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question: questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }


    public List<QuestionDTO> list(Integer page, Integer size) {

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds((page - 1) * size, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question: questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }

    public Integer getSizeByCreator(Integer creator){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        return  (int) questionMapper.countByExample(questionExample);
    }
    public Integer getSize(){
        QuestionExample questionExample = new QuestionExample();
        return (int)questionMapper.countByExample(questionExample);
    }



    public QuestionDTO getQuestionInfoById(Integer id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);

        return questionDTO;
    }

    public Question addOrUpdateQuestion(Question question) {
        if(question.getId() == null){
            addQuestion(question);
            return question;
        }else{
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());


            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            return question;
        }
    }
}
