package com.kdmins.login.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class TreeNode {
    public String pid;
    public String id;
    private String text;
    private String icon;
    private String href;
    private Map state;
    private List<TreeNode> nodes;
}