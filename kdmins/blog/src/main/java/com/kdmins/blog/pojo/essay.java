package com.kdmins.blog.pojo;

import lombok.Data;

import java.util.List;

@Data
public class essay {
    Integer essayId;
    String title;
    String author;
    List<comment> comments;
}
