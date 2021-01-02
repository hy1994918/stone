package com.kdmins.xls.annotation;

import java.lang.annotation.*;

/**
 *
 * @author zhangwei_david
 * @version $Id: Date.java, v 0.1 2015年5月29日 下午10:00:20 zhangwei_david Exp $
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XlsColInfo {
    String label() default "";
    int    sort();
    boolean blank() default false; //是否效验
    boolean verify() default false; //是否效验
}