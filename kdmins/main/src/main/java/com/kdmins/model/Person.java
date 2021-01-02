package com.kdmins.model;
import com.kdmins.xls.annotation.XlsClassInfo;
import com.kdmins.xls.annotation.XlsColInfo;
import lombok.Data;

import java.util.Date;

@Data
@XlsClassInfo(startCol = "B",endCol = "F",dataStartRow = 3)
public class Person {
    @XlsColInfo(label = "编号",sort =1,blank = true)
    private String no;  // 姓名
    @XlsColInfo(label = "姓名",sort = 2)
    private String name;  // 姓名
    @XlsColInfo(label = "时间",sort = 3)
    private Date time;  // 姓名
    @XlsColInfo(label = "产品线",sort = 4)
    private String product;  // 姓名
    @XlsColInfo(label = "部门",sort = 5)
    private String dept;  // 姓名
}
