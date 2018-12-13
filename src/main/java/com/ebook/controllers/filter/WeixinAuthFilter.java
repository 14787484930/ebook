package com.ebook.controllers.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;


@WebFilter("/*")
public class WeixinAuthFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/*
	 * Session中不存在
	 * 必须是微信duan
	 *     如果获取Code发送获取用户的请求
	 *     如果没有获取到发送授权请假
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;
        System.out.println("====================123");
        String agent = hRequest.getHeader("User-Agent");
        //如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
        String fromUserName = (String) hRequest.getSession().getAttribute("fromUserName");
        if (fromUserName == null)
        {
        	  //只有在微信端才做里面的操作
            if (agent != null && agent.toLowerCase().indexOf("micromessenger") >= 0)
            {
                String code = request.getParameter("code");
                String state = request.getParameter("state");
                //如果code不为空，scope为base,scope为userInfo代表用户已经同意
                if (code != null && state != null && state.equals("1"))
                {
                	System.out.println("1111111111111");
                    // 通过Code获取openid来进行授权
                    String url =  Constant.GET_USERINFO_URL
                    		.replace("APPID", Constant.APPID)
                    		.replace("SECRET", Constant.APPSECRET)
                    		.replace("CODE", code);
                    String json = SendHttp.sengGet(url);
                    String openid = JSONObject.fromObject(json).getString("openid");
                    hRequest.getSession().setAttribute("fromUserName", openid);
                    System.out.println(openid+"jjjjjjjjjjjjjjj");
                }
                else
                {
                	System.out.println("2222222222222222");
                	  //发送用户同意的请求
                    String path = hRequest.getRequestURL().toString();
                    String query = hRequest.getQueryString();
                    if (query != null)
                    {
                        path = path + "?" + query;
                    }
                    System.out.println(path);
                    String uri = Constant.WEB_AUTH_URL
                    		.replace("APPID", Constant.APPID)
                    		//回调的URL必须在回调域名里面
                    		.replace("REIRECT_URI", URLEncoder.encode(path, "UTF-8"))
                            .replace("SCOPE", "snsapi_userinfo")
                            .replace("STATE", "1");
                    hResponse.sendRedirect(uri);
                    return;
                }
            }
        }
        chain.doFilter(hRequest, hResponse);
    }

	@Override
	public void destroy() {

	}

}
