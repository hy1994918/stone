package com.kdmins.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
  * @Author: yao
  * @Description:
  * @Date:Create：in  2020/4/26 14:07
  * @Modified By：
  */
@ApiModel(value="com-kdmins-sysparameter-datasource-pojo-SysDatasourceProperties")
@Data
public class SysDatasourceProperties implements Serializable {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    private Integer id;

    /**
    * 数据源ID
    */
    @ApiModelProperty(value="数据源ID")
    private String dbId;

    /**
    * 连接地址
    */
    @ApiModelProperty(value="连接地址")
    private String dbUrl;

    /**
    * 登录名
    */
    @ApiModelProperty(value="登录名")
    private String dbUsername;

    /**
    * 登录密码
    */
    @ApiModelProperty(value="登录密码")
    private String dbPassword;

    /**
    * 数据库驱动
    */
    @ApiModelProperty(value="数据库驱动")
    private String dbDriver;

    /**
    * 初始化连接个数
    */
    @ApiModelProperty(value="初始化连接个数")
    private Integer dbInitaisize;

    /**
    * 最大连接池数量
    */
    @ApiModelProperty(value="最大连接池数量")
    private Integer dbMaxactive;

    /**
    * 最小连接池数量
    */
    @ApiModelProperty(value="最小连接池数量")
    private Integer dbMinidle;

    /**
    * 最大等待时间
    */
    @ApiModelProperty(value="最大等待时间")
    private Integer dbMaxwait;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createDate;

    private static final long serialVersionUID = 1L;
}
