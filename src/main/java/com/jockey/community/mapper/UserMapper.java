package com.jockey.community.mapper;

import com.jockey.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO user (id,account_id,name,token,gmt_create,gmt_modified) VALUES(#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

}
