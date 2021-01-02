package com.kdmins.login.shiro;

import com.kdmins.common.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {


    private static final String AUTHORIZATION ="Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "cookie";


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // TODO Auto-generated method stub
       if(CorsUtils.isCorsRequest((HttpServletRequest) request)){
            String token=((HttpServletRequest) request).getHeader("token");
            if(token==null){

            }else{
                String sessionId= JwtUtil.getSessionId(token);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
                return sessionId;
            }

       }else{

       }

       return super.getSessionId(request,response);
    }

}

