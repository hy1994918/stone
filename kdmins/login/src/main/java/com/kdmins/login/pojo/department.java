package com.kdmins.login.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class department {
    Integer id;
    String name;
    Date  createTime;
    Date  updateTime;
    Integer pId;
    Integer status;
    List<department> children=new ArrayList<department>();
}
