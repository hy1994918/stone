package com.kdmins.login.excepction;

import com.kdmins.common.base.BaseResult;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：lsy
 * @date ：Created in 2019/9/19 17:50
 * @modified By：
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Object Exception(HttpServletRequest request, Exception ex){

        return BaseResult.fail(503,"没有权限无法访问");
    }
}
