package com.kdmins.common.pojo;

import lombok.Data;

@Data
public class sendMessageResult {

    Integer resultCode;
    String message;

    public sendMessageResult(Integer resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
}
