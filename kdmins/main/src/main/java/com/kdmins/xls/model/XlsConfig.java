package com.kdmins.xls.model;

import lombok.Data;

import java.util.Map;

@Data
public class XlsConfig {
    String name;

    public XlsConfig(String name, int startCol, int endCol, int dataStartRow, int tableHeadRow) {
        this.name = name;
        this.startCol = startCol;
        this.endCol = endCol;
        this.dataStartRow = dataStartRow;
        this.tableHeadRow = tableHeadRow;

    }

    int   startCol;
    int   endCol;
    int   dataStartRow;
    int   tableHeadRow;
    Map<Integer, XlsColHeadConfigModel> xlsColHeadConfig;
}
