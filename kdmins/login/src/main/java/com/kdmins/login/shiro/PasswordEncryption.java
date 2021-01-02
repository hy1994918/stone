package com.kdmins.login.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密类 md5 的方式
 * @author ：lsy
 * @date ：Created in 2019/11/15 11:10
 * @modified By：
 */
public class PasswordEncryption {



    public static String passwordMd5(String username,String password){
        //加密方式
        String hashAlgorithmName = "md5";
        //传入的密码
        Object source = password;
        //盐值(账号+密码+kdmins)
        String salt = username+password+"kdmins";
        //加密次数
        String hashIterations = "1024";
        //md5 加密 , 密码 ,盐值,次数为1024
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName,password,salt,1024);
        return simpleHash.toString();
    }

    public static void main(String[] args) {
        String test = PasswordEncryption.passwordMd5("admin", "123456");
        System.out.println(test);
    }
}
