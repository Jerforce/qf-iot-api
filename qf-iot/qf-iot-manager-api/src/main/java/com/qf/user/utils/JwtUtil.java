package com.qf.user.utils;


import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qf.user.entity.User;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {
    private static final String salt = "CNM";
    public static String generatorMINUTEToken(Object user,Integer expire){

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE,expire);

        //颁发jwt令牌
        String token = JWT.create()
                //指定头部
                //.withHeader()
                //指定payload
                .withClaim("user", JSONUtil.toJsonStr(user))
                //指定过期时间
                .withExpiresAt(instance.getTime())
                //签名
                .sign(Algorithm.HMAC256(salt));

        return token;
    }

    public static String generatorHoursToken(Object user,Integer expire){

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.HOUR,expire);

        //颁发jwt令牌
        String token = JWT.create()
                //指定头部
                //.withHeader()
                //指定payload
                .withClaim("user", JSONUtil.toJsonStr(user))
                //指定过期时间
                .withExpiresAt(instance.getTime())
                //签名
                .sign(Algorithm.HMAC256(salt));

        return token;
    }
    /**
     * 校验令牌
     */

    public static User verifyToken(String token){
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(salt)).build();
            DecodedJWT verify = jwtVerifier.verify(token);
        return  JSONUtil.toBean(verify.getClaim("user").asString(),User.class);
    }
}
