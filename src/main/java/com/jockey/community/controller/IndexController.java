package com.jockey.community.controller;


import com.jockey.community.dto.PaginationDTO;
import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.model.User;
import com.jockey.community.service.NotificationService;
import com.jockey.community.service.QuestionService;
import com.jockey.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/")
    public String index(HttpServletRequest request
                        , Model model
                        ,@RequestParam(value = "page",defaultValue = "1") Integer page
                        ,@RequestParam(value = "size",defaultValue = "5") Integer size
                        ,@RequestParam(required = false)String search){

        if (page < 1) page = 1;
        if (size < 1) size = 1;
        Integer totalSize = questionService.getSize(search);
        Integer totalPages = (totalSize + size - 1)/size;
        if(page > totalPages) page = totalPages;

        List<QuestionDTO> questionDTOs = questionService.list(search,page,size);
        
        PaginationDTO paginationDTO = new PaginationDTO(questionDTOs, page, totalPages, totalSize);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("search", search);

        return "index";
    }

}
