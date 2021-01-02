package com.kdmins.login.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdmins.common.base.BaseResult;
import com.kdmins.common.util.TokenPermsUtil;
import com.kdmins.common.util.TokenSubjectUtil;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * @author ：lsy
 * @date ：Created in 2019/11/29 11:26
 * @modified By：
 */
public class PermsFilter extends AuthorizationFilter {

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        response.setCharacterEncoding("utf-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");//获得请求头
        //获得这个token 的权限
        Set perms = TokenPermsUtil.getPerms(token);
        //对比 如果有则通过 (mappedValue  是shiroConfig 里面拦截的 perms[****] 权限)
        if (!StringUtils.isEmpty(perms)){
            String[] string = (String[]) mappedValue;
            for (int i = 0; i <string.length ; i++) {
                String name = string[i];
                System.out.println(name);
                if (perms.contains(name)){
                    return true;
                }
            }
        }
       return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");//获得请求头

        if(TokenSubjectUtil.getSubject(token)!=null){
            String string = objectMapper.writeValueAsString(BaseResult.success(503, "您没有权限", null));
            response.getWriter().write(string);
            return false;
        }else {
            String string = objectMapper.writeValueAsString(BaseResult.success(501, "token不存在或已过期", null));
            response.getWriter().write(string);
            return false;
        }
    }
}
