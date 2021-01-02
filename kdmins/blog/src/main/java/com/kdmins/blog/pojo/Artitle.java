package com.kdmins.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;

@Data
public class Artitle {
    java.util.LinkedList LinkedList;
    String author;
    String id;
    String name;
    String groupId;
    String text;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    Date updateTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    Date createTime;
    String artitleShort;
    String prompt;
    Integer isRecommend;
    Integer recommendLevel;
}
