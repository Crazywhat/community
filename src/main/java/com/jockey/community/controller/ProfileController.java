package com.jockey.community.controller;


import com.jockey.community.dto.PaginationDTO;
import com.jockey.community.dto.QuestionDTO;
import com.jockey.community.model.User;
import com.jockey.community.service.QuestionService;
import com.jockey.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;


    @GetMapping("profile")
    public String profile(){
        return "redirect:/profile/questions";
    }


    @GetMapping("/profile/{section}")
    public String profileSection(@PathVariable("section")String section
                                 , @RequestParam(value = "page",defaultValue = "1") Integer page
                                 , @RequestParam(value = "size",defaultValue = "5") Integer size
                                 , Model model
                                 , HttpServletRequest request){

        if(section == null || section.isEmpty())
            section = "questions";

        if(page < 1) page = 1;
        if(size < 1) size = 1;

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return "redirect:/";

        if(section.equals("questions")){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

            Integer totalSize = questionService.getSizeByCreator(user.getId());
            Integer totalPages = (totalSize + size - 1) / size;
            if(page > totalPages) page = totalPages;

            List<QuestionDTO> questionDTOs = questionService.listByCreator(user.getId(), page, size);

            PaginationDTO paginationDTO = new PaginationDTO(questionDTOs, page, totalPages, totalSize);
            model.addAttribute("pagination", paginationDTO);


        }else if(section.equals("replies")){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }

}
