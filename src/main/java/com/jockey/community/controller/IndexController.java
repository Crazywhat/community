package com.jockey.community.controller;


import com.jockey.community.model.User;
import com.jockey.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0)
        {
            for ( Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userService.getUserByToken(token);
                    if(user != null)
                        request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }
        return "index";
    }

}
