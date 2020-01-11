package com.ebook.controllers.filter;

import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;
import com.ebook.services.UserService;
import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@WebFilter("/*")
public class WeixinAuthFilter implements Filter {


    private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

        ServletContext context = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(context);
        userService = (UserService) cxt.getBean(UserService.class);
    }

	/*
	 * Session中不存在
	 * 必须是微信duan
	 *     如果获取Code发送获取用户的请求
	 *     如果没有获取到发送授权请假
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)  {

        UserQuery userQuery = new UserQuery();


        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;

        String agent = hRequest.getHeader("User-Agent");

        //如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
        String fromUserName = (String) hRequest.getSession().getAttribute("fromUserName");

        try {
        	  //只有在微信端才做里面的操作
            if (agent != null && agent.toLowerCase().indexOf("micromessenger") >= 0)
            {
                if(hRequest.getSession().getAttribute("userInfo") != null){

                    User user = (User)hRequest.getSession().getAttribute("userInfo");
                    //没有认证就跳去认证校验
                    if(user.getFlag() == 1 && StringUtils.isBlank(user.getStudNo())){
                        hRequest.getRequestDispatcher("/user/").forward(request,response);//跳转认证页面
                    }

                }else{
                    String code = request.getParameter("code");
                    String state = request.getParameter("state");
                    //如果code不为空，scope为base,scope为userInfo代表用户已经同意
                    if (code != null && state != null && state.equals("1"))
                    {
                        // 通过Code获取openid来进行授权
                        String url =  Constant.GET_USERINFO_URL
                                .replace("APPID", Constant.APPID)
                                .replace("SECRET", Constant.APPSECRET)
                                .replace("CODE", code);
                        //通过code和state获取openid
                        String json = SendHttp.sendGet(url);
                        String openid = JSONObject.fromObject(json).getString("openid");

                        //判断用户是否认证过
                        userQuery.setOpenId(openid);
                        User user = userService.getByOpenId(userQuery);

                        if(user != null){    //表示已经认证过
                            hRequest.getSession().setAttribute("userInfo",user);
                        }else{   //表示没有认证过

                            //通过openid获取access_token
                            String access_token = JSONObject.fromObject(json).getString("access_token");

                            //通过access_token和openid获取用户基本信息
                            String userUrl = Constant.GET_USER_URL.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid);
                            String jsonStr = SendHttp.sendGet(userUrl);
                            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
                            user = new User();
                            user.setFlag(Integer.parseInt(hRequest.getParameter("flag")));
                            user.setOpenId(jsonObject.getString("openid"));
                            user.setNickname(jsonObject.getString("nickname"));
                            user.setSex(jsonObject.getInt("sex") == 1 ? "男":"女");
                            System.out.println("打印数据："+jsonStr);

                            //将用户信息存入session
                            hRequest.setAttribute("userInfo",user);

                            //判断请求权限
                            if(user.getFlag() == 1){

                                hRequest.getRequestDispatcher("/user/checkinfo").forward(request,response);//跳转认证页面
                            }
                        }


                    }
                    else
                    {
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

            }else{

            }

            chain.doFilter(hRequest, hResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void destroy() {

	}

}
