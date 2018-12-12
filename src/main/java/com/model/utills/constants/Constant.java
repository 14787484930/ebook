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
    public static String ACCESSTOKEN = "16_wiydMT5NaVWiRJhddFx0FFx8uM5PP6eQFVM3g7b1BafqACWp_wNxctBY4b9rtq4c2kwpvvnXePpwkd7c9_fgtK3Zg99rOGBnDds_7o3khAsVsn85f7SyFWCk6p4LPYeAHAAEN";
    //查询菜单
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    //删除菜单
    public static final String DEL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    //添加菜单
    public static final String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

}
