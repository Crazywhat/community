package com.jockey.community.controller;

import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.model.Question;
import com.jockey.community.model.User;
import com.jockey.community.service.QuestionService;
import com.jockey.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(value = "id",required = false)Long id,
                        Model model){

        QuestionDTO questionDTO = questionService.getQuestionInfoById(id);

        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());

        return "publish";
    }


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String addQuestion(Question question
            , Model model
            , HttpServletRequest request){

        //回现数据
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        //获取用户登录信息
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("msg","请先登录");
            return "publish";
        }

        //判断输入是否合法
        if(question.getTitle() == null || question.getTitle().equals(""))
        {
            model.addAttribute("msg","[ 问题标题 ]不能为空");
            return "publish";
        }
        if(question.getDescription() == null || question.getDescription().equals(""))
        {
            model.addAttribute("msg","[ 问题补充 ]不能为空");
            return "publish";
        }
        if(question.getTag() == null || question.getTag().equals(""))
        {
            model.addAttribute("msg","[ 标签 ]不能为空");
            return "publish";
        }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.addOrUpdateQuestion(question);

        return "redirect:/";
    }

}
