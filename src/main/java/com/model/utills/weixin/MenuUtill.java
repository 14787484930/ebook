package com.model.utills.weixin;

import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * @author zxl
 * @date 2019/4/19 11:38
 * @describe
 * 微信自定义菜单
 */
public class MenuUtill {


    /**
     * zxl
     * @return
     * 添加菜单
     * 2019-04-19
     */
    public static boolean addMenu(){

        String url = Constant.ADD_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        String jsonParams = objToJsonstr();

        String jsonStr = SendHttp.sendPost(url,jsonParams);

        if((Integer)(JSONObject.fromObject(jsonStr).get("errcode")) == 0){
            return true;
        }

        return false;

        /*
            正常返回 {"errcode":0,"errmsg":"ok"}
            错误返回 {"errcode":40018,"errmsg":"invalid button name size"}
        */
    }

    /**
     * zxl
     * 删除菜单
     * 2019-04-19
     * */
    public static boolean delMenu(){

        String url = Constant.DEL_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);
        String str = SendHttp.sendGet(url);
        System.out.println(str);

        if((Integer)(JSONObject.fromObject(str).get("errcode")) == 0){
            return true;
        }

        return false;

        /*
         {"errcode":0,"errmsg":"ok"}
        */
    }

    /**
     * zxl
     * 查询自定义菜单
     * 2019-04-19
     * */
    public static String getMenu(){

        String url = Constant.GET_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        String menuStr = SendHttp.sendGet(url);

        return menuStr;

    }



    /**
     * zxl
     * @return
     * 拼接菜单
     * 2019-04-19
     * */
    public static String objToJsonstr(){

        //根菜单
        JSONObject root = new JSONObject();

        //一级菜单集合
        JSONArray oneLevelMenus = new JSONArray();

        //*一级菜单*//*
        //进入、校园趣事、关于我们
        JSONObject input = new JSONObject();
        input.put("name","进入");

        JSONObject advertising = new JSONObject();
        advertising.put("name","校园趣事");
        advertising.put("type","view");
        advertising.put("url","http://www.baidu.com");

        JSONObject aboutUs = new JSONObject();
        aboutUs.put("name","关于我们");

        //*二级菜单*//*
        //进入有连个二级菜单
        JSONArray twoLevelMenus1 = new JSONArray();

        //我要购买
        JSONObject toBuy = new JSONObject();
        toBuy.put("type","view");
        toBuy.put("name","我要购买");
        toBuy.put("url","http://47.106.222.50:8080/book?flag=0");

        //我要发布
        JSONObject toRealse = new JSONObject();
        toRealse.put("type","view");
        toRealse.put("name","我要发布");
        toRealse.put("url","http://47.106.222.50:8080/book?flag=1");

        //加入集合
        twoLevelMenus1.add(toBuy);
        twoLevelMenus1.add(toRealse);

        //将集合放到一级菜单下
        input.put("sub_button",twoLevelMenus1);

        //关于我们有两个二级菜单
        JSONArray twoLevelMenus2 = new JSONArray();

        //我要购买
        JSONObject introduceOur = new JSONObject();
        introduceOur.put("type","view");
        introduceOur.put("name","我的建议");
        introduceOur.put("url","http://47.106.222.50:8080/advice");

        //我要发布
        JSONObject myAdvice = new JSONObject();
        myAdvice.put("type","view");
        myAdvice.put("name","关于我们");
        myAdvice.put("url","http://47.106.222.50:8080/version");

        //加入集合
        twoLevelMenus2.add(introduceOur);
        twoLevelMenus2.add(myAdvice);

        //将集合加入一级菜单
        aboutUs.put("sub_button",twoLevelMenus2);

        oneLevelMenus.add(input);
        oneLevelMenus.add(advertising);
        oneLevelMenus.add(aboutUs);

        //*全部整合到根菜单下*//*
        root.put("button",oneLevelMenus);

        return root.toString();
    }

}
