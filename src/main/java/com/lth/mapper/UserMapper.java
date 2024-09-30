package com.lth.mapper;

import com.lth.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 更新用户头像
    @Update("update user set user_pic = #{avatarUrl}, update_time = NOW() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id); ;

    // 根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    // 添加用户
    @Insert("INSERT INTO user(username, password, create_time, update_time) VALUES(#{username}, #{md5String}, NOW(), NOW())")
    void add(String username, String md5String);

    // 更新用户数据
    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = NOW() where id = #{id}")
    void update(User user);

    // 更新用户密码
    @Update("update user set password = #{md5String}, update_time = NOW() where id = #{id}")
    void updatePwd(String md5String, Integer id);
}
