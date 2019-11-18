package com.jockey.community.service;

import com.jockey.community.mapper.UserMapper;
import com.jockey.community.model.User;
import com.jockey.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    public User addUser(User user){
        userMapper.insertSelective(user);

        return user;
    }


    public User getUserByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);

        return (users.size() == 0)?null:users.get(0);
    }

    public User addOrUpdateUser(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> gitHubUsers = userMapper.selectByExample(userExample);

        if(gitHubUsers.size() == 0){
            return addUser(user);
        }else{

            User updateUser = new User();
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setBio(user.getBio());
            updateUser.setToken(user.getToken());
            updateUser.setName(user.getName());
            updateUser.setGmtModified(user.getGmtModified());


            userMapper.updateByExampleSelective(updateUser, userExample);

            return user;
        }
    }
}
