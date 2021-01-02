package com.kdmins.common.pojo;
import lombok.Data;
import java.util.HashMap;
@Data
public class websocketData<T> {
    T data;
    String message;
    String operate;
}
