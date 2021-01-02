package com.kdmins.login.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.kdmins.common.base.BaseResult;
import com.kdmins.common.util.TokenSubjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 shiro jwt-token 拦截器
 *
 * @author ：lsy
 * @date ：Created in 2019/11/12 15:30
 * @modified By：
 */
public class JwtTokenFilter extends BasicHttpAuthenticationFilter {

    /**
     * 重写登录方法,验证请求头是否含有 token 标识,有则放行,没有则返回请登录
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //必须设置编码,否则识别不到请求头
        response.setCharacterEncoding("utf-8");
        //System.out.println("shiro-token认证");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //String token = httpServletRequest.getParameter("token"); //获得参数
        String token = httpServletRequest.getHeader("token");//获得请求头
        //System.out.println("请求头="+token);
        // Date tokenDate = TokenSubjectUtil.getTokenDate(token);
        Subject subject = TokenSubjectUtil.getSubject(token);
        if (subject != null) {
            return true;
        }
      /*  if(JwtUtil.verify(token)){
            return true;
        }*/
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");//获得请求头
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(BaseResult.success(501, "token不存在或已过期", null));
        response.getWriter().write(string);
        return false;
    }
}
