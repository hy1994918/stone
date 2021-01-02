package com.kdmins.xls.model;

import lombok.Data;

import java.util.List;

@Data
public class ReadResultModel {
    List data;
    boolean valid;
    String message;
}
