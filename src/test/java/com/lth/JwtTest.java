package com.lth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtTest {


    @Test
    public void TestGenerateJwt(){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");


        // 生成jwt令牌
        String token = JWT.create()
                // 设置头部信息
                .withClaim("username", claims)
                // 设置过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                // 设置加密算法和密钥
                .sign(Algorithm.HMAC256("lth"));

        System.out.printf(token);

    }


    @Test
    public void TestParseJwt(){

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VybmFtZSI6eyJpZCI6MSwidXNlcm5hbWUiOiLlvKDkuIkifSwiZXhwIjoxNzI3NzA0NTIzfQ." +
                "vcxo3k7cBmbkj_sMs37hXUT4ncIiJkym38xxR1k4Wng";

        // 生成一个解析后的jwt对象
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("lth")).build().verify(token);

        Map<String, Object> claims = decodedJWT.getClaim("username").asMap();
        System.out.printf(claims.toString());

    }
}
