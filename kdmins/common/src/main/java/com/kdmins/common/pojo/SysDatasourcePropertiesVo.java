package com.kdmins.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: yao
 * @Description:
 * @Date:Create：in 2020/4/26 13:50
 * @Modified By：
 */
@Data
public class SysDatasourcePropertiesVo {

    /**
     * 数据源名称
     */
    private String DbName;


    private Integer id;
    /**
     * 数据源ID
     */
    private String dbId;
    /**
     * 备注
     */
    private String node;
    /**
     * 连接地址
     */
    private String dbUrl;
    /**
     * 登录名
     */
    private String dbUsername;
    /**
     * 登录密码
     */
    private String dbPassword;
    /**
     * 数据库驱动
     */
    private String dbDriver;
    /**
     * 初始化连接个数
     */
    private Integer dbInitaisize;
    /**
     * 最大连接池数量
     */
    private Integer dbMaxactive;
    /**
     * 最小连接池数量
     */
    private Integer dbMinidle;
    /**
     * 最大等待时间
     */
    private Integer dbMaxwait;
    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * type
     */
    private String type;

}
