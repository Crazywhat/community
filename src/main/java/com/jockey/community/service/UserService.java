package com.jockey.community.service;

import com.jockey.community.mapper.UserMapper;
import com.jockey.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    public User addUser(User user){

        userMapper.insertUser(user);

        return user;
    }


    public User getUserByToken(String token) {
       return userMapper.selectUserByToken(token);
    }

    public User addOrUpdateUser(User user) {
        User gitUser = userMapper.selectUserByAccoutId(user.getAccountId());
        if(gitUser == null){
            return addUser(user);
        }else{
            userMapper.updateUser(user);
            return user;
        }
    }
}
