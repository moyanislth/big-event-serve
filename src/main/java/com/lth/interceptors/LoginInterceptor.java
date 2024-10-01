package com.lth.interceptors;

import com.lth.utils.JwtUtil;
import com.lth.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证请求头Authorization携带的token
        String token = request.getHeader("Authorization");

        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);

            ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
            String tokenInRedis = ops.get(token);

            if (tokenInRedis == null){
                throw new RuntimeException("token已过期");
            }

            ThreadLocalUtil.set(claims);

        } catch (Exception e) {
            response.setStatus(401);
            // 不放行
            return false;
        }


        // 放行
        return true;
    }

    @Override
    // 在请求处理之后进行调用（Controller方法调用之后）
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        // ThreadLocalUtil中存储的数据是线程级别的，使用完后需要清除
        ThreadLocalUtil.remove();
    }

}
