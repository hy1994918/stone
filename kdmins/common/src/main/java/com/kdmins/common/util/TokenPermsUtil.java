package com.kdmins.common.util;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.shiro.subject.Subject;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 *  放置权限的map
 * @author ：lsy
 * @date ：Created in 2019/12/2 9:16
 * @modified By：
 */
public class TokenPermsUtil {

    //private static final long EXPIRE_TIME = 30 * 60 * 1000;   //  半小时
    private static final long EXPIRE_TIME = 8*60*60 * 1000;   //  8小时

    // 放置权限 的 map
    private static ExpiringMap<String, Set> permsMap = ExpiringMap.builder().variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    /**
     * 把jwt-token 和 权限 以key,value的形式存进map中
     *
     * @param randomKey
     * @param perms
     */
    public static void savePerms(String randomKey, Set perms) {
        //每次调用都会刷新时间
        permsMap.put(randomKey, perms, ExpirationPolicy.ACCESSED, EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 根据token获取存放在map中的 shiro-subject
     *
     * @param key
     * @return
     */
    public static Set getPerms(String key) {
        return permsMap.get(key);
    }

    /**
     * 移除某个token
     * @param randomKey
     */
    public static  void remove(String randomKey){
        //移除token
        permsMap.remove(randomKey);
    }


    public static void main(String[] args) {

    }

}
