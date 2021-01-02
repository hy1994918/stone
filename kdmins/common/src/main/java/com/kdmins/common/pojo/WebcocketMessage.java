package com.kdmins.common.pojo;

import lombok.Data;

@Data
public class WebcocketMessage<T> {
    /*该字段暂时无卵用*/
    String id;
    /*1代表后台主动往前台发*/
    /*2代表前台主动往后台发*/
    Integer messageType;
    /*发起方希望接收方干什么*/
    String dealName;
    /*数据*/
    T  message;
}
