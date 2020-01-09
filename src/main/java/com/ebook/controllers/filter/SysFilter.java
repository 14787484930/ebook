package com.ebook.controllers.filter;

import com.ebook.beans.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxl
 * @date 2018/12/19 10:00
 * @describe
 */

//@WebFilter("/*")
public class SysFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;

        //如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
        User userInfo = (User) hRequest.getSession().getAttribute("userInfo");



        chain.doFilter(hRequest, hResponse);

    }

    @Override
    public void destroy() {

    }
}
