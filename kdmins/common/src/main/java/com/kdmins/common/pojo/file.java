package com.kdmins.common.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class file {
    Integer id;
    Integer groupId;
    String fileName;
    String fileLabel;
    Integer playNum;
    String fileSuffix;
    String filePath;
    @ApiModelProperty(hidden = true)
    @JSONField(serialize=false)
    int status;
    @ApiModelProperty(hidden = true)
    @JSONField(serialize=false)
    String message;
}
