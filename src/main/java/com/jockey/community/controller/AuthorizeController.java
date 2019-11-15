package com.jockey.community.controller;

import com.jockey.community.dto.AccessTokenDTO;
import com.jockey.community.dto.GithubUser;
import com.jockey.community.model.User;
import com.jockey.community.provider.GithubProvider;
import com.jockey.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.UUID;


@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private AccessTokenDTO accessTokenDTO;

    @Autowired
    private UserService userService;


    @GetMapping("/callback")
    public String callback(@PathParam("code")String code,
                           @PathParam("state")String state,
                           HttpServletResponse response
                           ){

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        String tokenString = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser githubUser = githubProvider.getGithubUser(tokenString);

        if(githubUser != null){

            String token = UUID.randomUUID().toString();

            User user = new User();
            user.setAccountId(githubUser.getId().toString());
            user.setName(githubUser.getLogin());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(user.getBio());

            response.addCookie(new Cookie("token",token));

            userService.addUser(user);
            System.out.println(user);
        }

        return "redirect:/";
    }
}
