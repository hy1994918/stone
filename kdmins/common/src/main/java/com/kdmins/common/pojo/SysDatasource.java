package com.kdmins.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
  * @Author: yao
  * @Description:
  * @Date:Create：in  2020/4/26 13:41
  * @Modified By：
  */
@ApiModel(value="com-kdmins-sysparameter-datasource-pojo-SysDatasource")
@Data
public class SysDatasource implements Serializable {
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
    * 数据源名称
    */
    @ApiModelProperty(value="数据源名称")
    private String dbName;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String node;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 数据库类型
     */
    @ApiModelProperty(value = "数据库类型")
    private String type;

    private static final long serialVersionUID = 1L;
}
