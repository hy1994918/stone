package com.kdmins.blog.controller;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class test {
    public static String getJbSql(String jsonObjStr) {
        String jbsql=" select   distinct     \r\n" +
                "               t4.explain as rules_name ,\r\n" +
                "               nvl(t4.filtrate_sql, t5.filtrate_sql) as filtrate_sql ,t4.queue_num\r\n" +
                "          from shf_dep_rules t4\r\n" +
                "         inner join shf_rules t5\r\n" +
                "            on t4.rules_id = t5.id\r\n" +
                "         where ";
        String[] jbtest=jsonObjStr.split("@@");
        String endSql="";
        if(jbtest.length==1) {
            endSql="t4.dept_item_id ="+jbtest[0].substring(1, jbtest[0].length()-1);

        }else {
            for(int i=0;i<jbtest.length;i++) {
                if(i==0) {
                    endSql="t4.dept_item_id ="+jbtest[0].substring(1)+" or ";
                }else if(i==jbtest.length-1) {
                    endSql=endSql+"t4.dept_item_id ="+jbtest[i].substring(0,jbtest[i].length()-1);
                }else {
                    endSql=endSql+"t4.dept_item_id ="+jbtest[i]+" or ";
                }

            }
        }

        return "select rules_name, filtrate_sql from ("+jbsql+endSql +" order by queue_num)";
    }
    public static void main(String[] args) {
      System.out.println( getJbSql("@2@"));
    }
}
