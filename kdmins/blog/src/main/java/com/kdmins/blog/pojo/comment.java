package com.kdmins.blog.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class comment {
   Integer commentsId;
   String context;
   Date commentTime;
   String commentUser;
}
