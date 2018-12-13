package com.model.utills.constants;

/**
 * ZXL
 * 2018-11-12
 * 常量工具类
 */
public  class Constant {

    /*定义模版*/
    /*public final static Integer CODE = 200;*/

   /*微信先关常量*/
    //获取ACCESS_TOKEN的url
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //第三方用户唯一凭证
    public static final String APPID = "wx3393132e8ecc0542";
    //第三方用户唯一凭证密钥
    public static final String APPSECRET = "332493c05a5932737400ada0f4a61109";
    //调用接口凭据
    public static String ACCESSTOKEN = "16_rBpHixvel54ZMcMAay4JYWHn7wJLh9qXWUc7i0Hr7UQpl-yViwwVAs8oj-0e37met_ydeqw2C-5BeQsEhxVJzPWzPME3WX078E6G4KyzMOFNQbNcYLWha4VpQyX43vCUiSuVvH8WMM3kLyRoAILfAAARZC";
    //查询菜单
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    //删除菜单
    public static final String DEL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    //添加菜单
    public static final String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //发起授权请求
    public static final String WEB_AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    //获取用户信息
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

}
