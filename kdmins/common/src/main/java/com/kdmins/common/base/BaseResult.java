package com.kdmins.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：lsy
 * @date ：Created in 2019/10/16 14:20
 * @modified By：
 */
@Data

@ApiModel(value = "返回统一格式", description = "新增用户参数")
public class BaseResult<T> implements Serializable {


    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;
    @ApiModelProperty(value = "响应代码")
    private int code;
    @ApiModelProperty(value = "附带信息")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 返回成功
     * @return
     */
    public static BaseResult success(){
        return BaseResult.createResult(STATUS_SUCCESS,"成功",null);
    }

    public static BaseResult success(Object data){
        return BaseResult.createResult(STATUS_SUCCESS,"成功",data);
    }

    public static BaseResult success(String message){
        return BaseResult.createResult(STATUS_SUCCESS,message,null);
    }

    public static BaseResult success(String message,Object data){
        return BaseResult.createResult(STATUS_SUCCESS,message,data);
    }

    public static BaseResult success(int code,String message,Object data){
        return BaseResult.createResult(code,message,data);
    }

    /**
     * 失败返回
     * @return
     */
    public static BaseResult fail(){
        return BaseResult.createResult(STATUS_FAIL,"失败",null);
    }

    public static BaseResult fail(String message){
        return BaseResult.createResult(STATUS_FAIL,message,null);
    }

    public static BaseResult fail(int code,String message){
        return BaseResult.createResult(code,message,null);
    }

    private static BaseResult createResult(int code, String message, Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMessage(message);
        baseResult.setData(data);

        return baseResult;
    }
}
