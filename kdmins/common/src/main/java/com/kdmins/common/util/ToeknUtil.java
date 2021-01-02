package com.kdmins.common.util;

import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * token封装类
 * @author ：lsy
 * @date ：Created in 2019/11/15 11:41
 * @modified By：
 */
public class ToeknUtil {

    //存放token
    private static Map<String, Object> tokenMap = new HashMap<>();

    /**
     * 返回token令牌
     * @param token
     * @return
     */
    public static Map setToken(String token){
        tokenMap.put("token",token);
        return tokenMap;
    }

    public static void  main(String[]args){
        String password = "123";
        System.out.println(System.currentTimeMillis());
        for(int i=0;i<1;i++){
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        }
        System.out.println(System.currentTimeMillis());

    }
}
