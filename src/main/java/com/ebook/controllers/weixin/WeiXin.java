package com.ebook.controllers.weixin;

import com.model.utills.sha1.Sha1Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Controller
@RequestMapping("/weixin/")
public class WeiXin {

    @RequestMapping(value="test",method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //response.getWriter().println("weixin");
        System.out.println("===================="+123);

        /*采用加密认证方式*/
        //一、获取密文认证的四个参数

        //signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");

        //timestamp 时间戳
        String timestamp = request.getParameter("timestamp");

        //nonce 随机数
        String nonce = request.getParameter("nonce");

        //echostr 随机字符串
        String echostr = request.getParameter("echostr");

        System.out.println("signature==="+signature);
        System.out.println("timestamp==="+timestamp);
        System.out.println("nonce==="+nonce);
        System.out.println("echostr==="+echostr);

        //二、对四个参数做密文认证的处理
        //将token、timestamp、nonce三个参数进行字典(数组)序排序
        String[] params = new String[]{"weixin",timestamp,nonce};
        Arrays.sort(params); //做字典排序

        //将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer sb = new StringBuffer();
        for(String str:params){
            sb.append(str);
        }
        String content = sb.toString();
        String secretContent = Sha1Util.sha1(content); //进行加密操作

        //开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(signature.equals(secretContent)){
            //认证成功 需回显echostr
            response.getWriter().print(echostr);
        }else{
            //认证失败 跑一个异常
            throw new RuntimeException("认证失败！");
        }
    }

    @RequestMapping(value="test",method = RequestMethod.POST)
    public void testPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置响应编码
        response.setContentType("text/html; charset=UTF-8");

        //从request中获取字节流
        ServletInputStream inputStream = request.getInputStream();

        //输出请求内容
        InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
        BufferedReader bf = new BufferedReader(reader);

        String content = null;
        while ((content = bf.readLine())!=null) {
            System.out.println(content);
        }

        //System.out.println("========"+666);
        //response.getWriter().print("谢谢！");

    }
}
