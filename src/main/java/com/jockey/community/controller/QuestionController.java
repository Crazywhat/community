package com.jockey.community.controller;

import com.jockey.community.dto.CommentDTO;
import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.enums.CommentType;
import com.jockey.community.model.Question;
import com.jockey.community.service.CommentService;
import com.jockey.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Long id
                            , Model model){

        QuestionDTO questionDTO = questionService.getQuestionInfoById(id);
        questionService.increaseViewCount(id);
        model.addAttribute("question",questionDTO);

        List<CommentDTO> commentDTOs = commentService.listByTargetId(id, CommentType.QUESTION);
        model.addAttribute("comments", commentDTOs);

        List<Question> tagRelatedQuestion = questionService.getTagRelatedQuestion(questionDTO.getId(),questionDTO.getTag());
        model.addAttribute("related",tagRelatedQuestion);

        return "question";
    }

}
