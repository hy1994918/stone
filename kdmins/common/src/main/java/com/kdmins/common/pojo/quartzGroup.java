package com.kdmins.common.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class quartzGroup {
    Integer id;
    String name;
    String jobClass;
    String remark;
    Date   createTime;
    Date   updateTime;
}
