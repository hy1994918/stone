package com.kdmins.common.base;

import lombok.Data;

import java.util.List;

@Data
public class pageInfo {
    int currentPage;
    long pageNum;
    long total;
    List data;
}
