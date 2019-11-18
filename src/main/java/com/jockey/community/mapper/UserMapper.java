package com.jockey.community.mapper;

import com.jockey.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO user (id,account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) VALUES(#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE token=#{token}")
    User selectUserByToken(@Param("token") String token);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectUserById(@Param("id") int id);

    @Select("SELECT * FROM user WHERE account_id=#{accountId}")
    User selectUserByAccoutId(@Param("accountId") String accountId);


    @Update({"UPDATE user SET name=#{name},token=#{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} WHERE account_id=#{accountId}"})
    void updateUser(User user);
}
