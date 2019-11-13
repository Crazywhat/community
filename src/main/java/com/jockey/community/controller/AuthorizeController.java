package com.jockey.community.controller;

import com.jockey.community.dto.AccessTokenDTO;
import com.jockey.community.dto.GithubUser;
import com.jockey.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;


@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Autowired
    AccessTokenDTO accessTokenDTO;


    @GetMapping("/callback")
    public String callback(@PathParam("code")String code,
                           @PathParam("state")String state,
                           HttpServletRequest request
                           ){

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        String tokenString = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(tokenString);

        GithubUser user = githubProvider.getGithubUser(tokenString);

        if(user != null){
            request.getSession().setAttribute("user", user);
        }

        return "redirect:/";
    }
}
