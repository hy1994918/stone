package com.kdmins.login.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value="com.kdmins.login.pojo.SysProgfuncs")
@Data
public class SysProgfuncs implements Serializable {
    @ApiModelProperty(value="null")
    private Long id;

    @ApiModelProperty(value="null")
    private Long lastId;

    @ApiModelProperty(value="null")
    private Long modId;

    @ApiModelProperty(value="null")
    private String caption;

    @ApiModelProperty(value="null")
    private String explanation;

    @ApiModelProperty(value="null")
    private Integer sysNum;

    @ApiModelProperty(value="null")
    private String part;

    @ApiModelProperty(value="null")
    private Integer sortNum;

    @ApiModelProperty(value="null")
    private String controlid;

    @ApiModelProperty(value="null")
    private String pathUrl;

    @ApiModelProperty(value="null")
    private String componentPath;

    @ApiModelProperty(value="null")
    private String adminAddress;

    private static final long serialVersionUID = 1L;
}