package com.jockey.community.mapper;

import com.jockey.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO user (id,account_id,name,token,gmt_create,gmt_modified,bio) VALUES(#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insertUser(User user);

    @Select("SELECT * FROM PUBLIC.user WHERE token=#{token}")
    User selectUserByToken(@Param("token") String token);
}
