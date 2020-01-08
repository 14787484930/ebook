package com.ebook.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeiXinToken {

    //调用接口凭据
    @Autowired
    public static String ACCESSTOKEN = "";

}
