package com.kdmins.xls.model;

import lombok.Data;

@Data
public class XlsColHeadConfigModel {
    String label;
    String property;
    int sort;
    boolean verity;
    boolean blank;
    Class  fieldType;
    public XlsColHeadConfigModel(String label, String property, int sort, boolean verity, boolean blank, Class fieldType) {
        this.label = label;
        this.property = property;
        this.sort = sort;
        this.verity=verity;
        this.blank=blank;
        this.fieldType=fieldType;
    }
}
