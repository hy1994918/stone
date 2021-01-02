package com.kdmins.stay.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
@Data
public class stayManager {
    @JSONField(format = "yyyy-hh-dd")
    Date registrationTime;
    String userName;
    String userIdCard;
    String phone;
    String roomNo;
    String roomCharge;
    String deposit;
    Float memberCharge;
    Float userRefund;
    String roomType;
    String roomConsumption;
    String shop;
    @JSONField(name = "isEdit")
    boolean isEdit;
    Integer id;
}
