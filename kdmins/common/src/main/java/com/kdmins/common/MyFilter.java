package com.kdmins.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
@Configuration
@WebFilter(filterName = "myFilter", urlPatterns = "/*")*/
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setHeader("Access-Control-Allow-Headers","token");
            PrintWriter writer = response.getWriter();



        }else{
            chain.doFilter(request, response);
        }



    }

    @Override
    public void destroy() {
    }
}