package com.jockey.community.controller;

import com.jockey.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping("/notification/{id}")
    public String getNotice(@PathVariable("id")Long id){
        Long questionId = notificationService.Read(id);
        return "redirect:/question/" + questionId;
    }

}
