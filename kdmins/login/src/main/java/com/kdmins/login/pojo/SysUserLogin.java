package com.kdmins.login.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysUserLogin implements Serializable {
    private Integer id;

    private String userCode;

    private String loginName;

    private String loginPsw;

    private Integer errorCount = 0;

    private Integer userFlag = 0;

    private static final long serialVersionUID = 1L;
}