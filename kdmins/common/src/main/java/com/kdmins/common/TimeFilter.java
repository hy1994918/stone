package com.kdmins.common;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(urlPatterns = "/users/*",filterName = "TimeFilter")
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        System.out.println("TimeFilter start");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("TimeFilter finish");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy");
    }
}
