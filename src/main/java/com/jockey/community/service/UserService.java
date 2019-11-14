package com.jockey.community.service;

import com.jockey.community.mapper.UserMapper;
import com.jockey.community.mapper2.UserMapper2;
import com.jockey.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserMapper2 userMapper2;

    public User addUser(User user){

        User userTemp = new User();
        BeanUtils.copyProperties(user,userTemp);

        if(userMapper != null)
            userMapper.insertUser(user);

        userMapper2.insertUser(userTemp);
        return user;
    }


}
