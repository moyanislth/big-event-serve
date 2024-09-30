package com.lth.service;


import com.lth.pojo.User;

public interface UserServive {

    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册用户
    void register(String username, String password);

    // 更新用户数据
    void update(User user);

    // 更新用户头像
    void updateAvatar(String avatarUrl);

    // 更新用户密码
    void updatePwd(String newPwd);
}
