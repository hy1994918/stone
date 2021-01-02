package com.kdmins.stay.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class stayInfoDate {
    @JSONField(format = "yyyy-mm-dd")
    Date date;
    List<stayManager> stayInfos;
    List<OutInfo> outInfos;
}
