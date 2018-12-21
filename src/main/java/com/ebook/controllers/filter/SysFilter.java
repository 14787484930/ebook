package com.ebook.controllers.filter;

import com.ebook.beans.user.User;
import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

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

        if(userInfo == null) {
            //如果session中不存在用户信息，创建用户信息
            User user = new User();
            user.setId("00cc34833799460fb4e9ea7fbc5c1da2");
            user.setNickname("小野菊");
            user.setWeiXin("weixin");
            user.setPhone("14787484930");
            user.setOpenId("bbb");
            hRequest.getSession().setAttribute("userInfo",userInfo);
        }


        chain.doFilter(hRequest, hResponse);

    }

    @Override
    public void destroy() {

    }
}