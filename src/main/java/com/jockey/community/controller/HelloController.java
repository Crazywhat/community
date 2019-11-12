package com.jockey.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;


@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@PathParam("name") String name, Model model){
        model.addAttribute("hello",name);
        return "hello";
    }
}
