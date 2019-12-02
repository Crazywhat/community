package com.jockey.community.service;

import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.dto.QuestionSearchDTO;
import com.jockey.community.exception.CustomizeErrorCode;
import com.jockey.community.exception.CustomizeException;
import com.jockey.community.mapper.QuestionExtendMapper;
import com.jockey.community.mapper.QuestionMapper;
import com.jockey.community.mapper.UserMapper;
import com.jockey.community.model.Question;
import com.jockey.community.model.QuestionExample;
import com.jockey.community.model.User;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    QuestionExtendMapper questionExtendMapper;


    public Question addQuestion(Question question){
        questionMapper.insertSelective(question);
        return question;
    }


    public List<QuestionDTO> listByCreator(Long creator,Integer page, Integer size){
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


    public List<QuestionDTO> list(String search, Integer page, Integer size) {

        String searchString = null;

        if(!StringUtils.isBlank(search)){
            String[] keys = search.split(" ");
            searchString = String.join("|",keys);
        }

        QuestionSearchDTO questionSearchDTO = new QuestionSearchDTO();
        questionSearchDTO.setSearch(searchString);
        questionSearchDTO.setOffset((page - 1) * size);
        questionSearchDTO.setSize(size);

        List<Question> questions = questionExtendMapper.selectBySearch(questionSearchDTO);
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

    public Integer getSizeByCreator(Long creator){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        return  (int) questionMapper.countByExample(questionExample);
    }
    public Integer getSize(String search){
        String searchString = null;
        if(!StringUtils.isBlank(search)){
            String[] keys = search.split(" ");
            searchString = String.join("|",keys);
        }
        return questionExtendMapper.countBySearch(searchString);
    }



    public QuestionDTO getQuestionInfoById(Long id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NO_EXIST);
        }

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

            if (questionMapper.updateByExampleSelective(updateQuestion, questionExample) != 1)
                throw new  CustomizeException(CustomizeErrorCode.QUESTION_NO_EXIST);

            return question;
        }
    }

    public void increaseViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtendMapper.increaseViewCount(question);
    }

    public List<Question> getTagRelatedQuestion(Long id,String tag) {
        String[] tags = tag.split(",");
        String relatedTag = String.join("|",tags);

        Question question = new Question();
        question.setId(id);
        question.setTag(relatedTag);

        List<Question> relatedQuestion = questionExtendMapper.selectByRelatedTag(question);
        if(relatedQuestion.size() == 0){
            return new ArrayList<>();
        }else {
            return relatedQuestion;
        }
    }
}
