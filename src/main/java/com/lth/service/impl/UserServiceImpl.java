package com.lth.service.impl;

import com.lth.mapper.UserMapper;
import com.lth.pojo.User;
import com.lth.service.UserServive;
import com.lth.utils.Md5Util;
import com.lth.utils.ThreadLocalUtil;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserServive {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        // 加密密码
        String md5String = Md5Util.getMD5String(password);

        // 注册用户
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        log.info("更新用户头像，id：{}，头像地址：{}",id,avatarUrl);
        userMapper.updateAvatar(avatarUrl,id);
    }

    // 更新用户密码
    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
