package com.kdmins.xls.annotation;

import java.lang.annotation.*;

/**
 *
 * @author zhangwei_david
 * @version $Id: Date.java, v 0.1 2015年5月29日 下午10:00:20 zhangwei_david Exp $
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XlsClassInfo {
    String name() default "";
    String   startCol() default "A";
    String   endCol()  ;
    int   dataStartRow() default 1;
    int   tableHeadRow() default 0;
}