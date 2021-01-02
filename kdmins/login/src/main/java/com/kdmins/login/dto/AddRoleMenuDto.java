package com.kdmins.login.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ：lsy
 * @date ：Created in 2020/4/15 14:58
 * @modified By：
 */
@Data
public class AddRoleMenuDto {

    private Integer RoleId;

    private List<Integer> menuIdList;
}
