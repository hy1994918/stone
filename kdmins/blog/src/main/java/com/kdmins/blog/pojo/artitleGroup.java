package com.kdmins.blog.pojo;

import lombok.Data;

import java.util.List;

@Data
public class artitleGroup {
    String id;
    String artitleGroupName;
    String artitleGroupRemark;
    List<Artitle> artitle;
    Integer num;
}
