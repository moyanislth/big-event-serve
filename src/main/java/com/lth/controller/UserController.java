package com.lth.controller;

import com.lth.pojo.Result;
import com.lth.pojo.User;
import com.lth.service.UserServive;
import com.lth.utils.JwtUtil;
import com.lth.utils.Md5Util;
import com.lth.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserServive userServive;

    @PostMapping("/register")
    // 正则表达式校验用户名和密码，\S非空，长度5-16
    public Result register(@NotNull @Pattern(regexp = "^\\S{5,16}$") String username, @NotNull @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户
        User user = userServive.findByUserName(username);
        if (user == null){
            // 不存在则注册
            userServive.register(username, password);
            return Result.success();
        }else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){

        User user = userServive.findByUserName(username);

        if (user == null){
            return Result.error("用户名不存在");
        }else if (!user.getPassword().equals(Md5Util.getMD5String(password))){
            return Result.error("密码错误");
        }else {
            // 登录成功，返回jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", username);

            // 生成jwt令牌
            String jwt = JwtUtil.genToken(claims);

            return Result.success(jwt);
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader("Authorization") String token){

        Map<String, Object> claims = ThreadLocalUtil.get();

        User user = userServive.findByUserName(claims.get("username").toString());


        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userServive.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userServive.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasText(oldPwd) || !StringUtils.hasText(newPwd) || !StringUtils.hasText(rePwd)){
            return Result.error("缺少必要参数");
        }else if (!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致");
        }

        Map<String, Object> claims = ThreadLocalUtil.get();
        User user = userServive.findByUserName(claims.get("username").toString());
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }else {
            userServive.updatePwd(newPwd);
        }

        return Result.success();

    }


}

