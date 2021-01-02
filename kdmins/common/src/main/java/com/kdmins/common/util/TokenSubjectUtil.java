package com.kdmins.common.util;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.shiro.subject.Subject;

import java.util.concurrent.TimeUnit;

/**
 * jwt-token 工具类
 * @author ：lsy
 * @date ：Created in 2019/11/12 15:01
 * @modified By：
 */
public class TokenSubjectUtil {

    //private static final long EXPIRE_TIME =   30*60 * 1000;   //  半小时
    private static final long EXPIRE_TIME = 8*60*60 * 1000;   //  8小时

    // 放置shiro主体的 map
    private static ExpiringMap<String, Subject> subjectMap = ExpiringMap.builder().variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();
    /**
     * 把jwt-token 和 shiro-subject 以key,value的形式存进map中
     * @param randomKey
     * @param sub
     */
    public static  void saveSubject(String randomKey,Subject sub){
        //每次调用都会刷新时间
        subjectMap.put(randomKey,sub,ExpirationPolicy.ACCESSED,EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 移除某个token
     * @param randomKey
     */
    public static  void removeSubject(String randomKey){
        //移除token
        subjectMap.remove(randomKey);
    }

    /**
     * 根据token获取存放在map中的 shiro-subject
     * @param key
     * @return
     */
    public static  Subject getSubject(String key){
        return subjectMap.get(key);
    }


}
