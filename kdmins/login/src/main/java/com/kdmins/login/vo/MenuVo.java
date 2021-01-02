package com.kdmins.login.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 返回的菜单
 * @author ：lsy
 * @date ：Created in 2020/4/14 14:45
 * @modified By：
 */
@Data
public class MenuVo {


    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("父级id")
    private Integer lastId;

    @ApiModelProperty("菜单名称")
    private String caption;

    @ApiModelProperty("跳转地址")
    private String pathUrl;

    @ApiModelProperty("组件地址")
    private String componentPath;

    @ApiModelProperty("标识")
    private BigDecimal checked;

    @ApiModelProperty("子集")
    private List<MenuVo> children;

}
