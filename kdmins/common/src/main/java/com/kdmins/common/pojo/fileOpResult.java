package com.kdmins.common.pojo;

import lombok.Data;

@Data
public class fileOpResult {
    Boolean flag;
    String message;

    public fileOpResult(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }
    public fileOpResult() {

    }
}
