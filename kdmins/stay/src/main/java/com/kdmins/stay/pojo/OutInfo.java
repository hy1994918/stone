package com.kdmins.stay.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class OutInfo {
    Date outDate;
    Integer id;
    Double outNum;
    String outItem;
    @JSONField(name = "isEdit")
    boolean isEdit;
}
