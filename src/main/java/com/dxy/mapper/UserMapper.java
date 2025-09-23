package com.dxy.mapper;

import com.dxy.data.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectUser(Long id);

    @Insert("INSERT INTO users(名字, email) VALUES(#{名字}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @Insert("INSERT INTO users (名字, email, created_at) VALUES (#{名字}, #{email}, #{createTime})")
    int insert(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(Long id);
}