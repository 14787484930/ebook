package com.model.utills.weixin;

import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;

/**
 * @author zxl
 * @date 2019/1/17 10:13
 * @describe
 * 向用户推送图文消息
 */
public class SendMessage {

    /**
     *
     * @param url
     * @param openId
     * @param media_id
     * @return
     * 预览图文消息
     */
    public static String previewMessage(String url,String openId,String media_id){

        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        JSONObject jsonObject = new JSONObject();

        //filter
        JSONObject jsonFilter = new JSONObject();
        jsonFilter.put("is_to_all",true);
        //jsonFilter.put("tag_id",2);

        //mpnews
        JSONObject jsonMpnews = new JSONObject();
        jsonMpnews.put("media_id",media_id);

        jsonObject.put("mpnews",jsonMpnews);

        //msgtype
        jsonObject.put("msgtype","mpnews");

        //send_ignore_reprint
        jsonObject.put("send_ignore_reprint",0);

        String jsonStr = jsonObject.toString();

        System.out.println(jsonStr);

        jsonObject.put("touser",openId);
        return SendHttp.sendPost(url,jsonStr);

    }

    /**
     *
     * @param url
     * @param openId
     * @param media_id
     * @param tag_id
     * @return
     * 推送图文消息
     */
    public static String pushMessage(String url,String openId,String media_id,String tag_id){

        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        JSONObject jsonObject = new JSONObject();

        //filter
        JSONObject jsonFilter = new JSONObject();
        jsonFilter.put("is_to_all",true);
        jsonFilter.put("tag_id",tag_id);

        jsonObject.put("filter",jsonFilter);

        //mpnews
        JSONObject jsonMpnews = new JSONObject();
        jsonMpnews.put("media_id",media_id);

        jsonObject.put("mpnews",jsonMpnews);

        //msgtype
        jsonObject.put("msgtype","mpnews");

        //send_ignore_reprint
        jsonObject.put("send_ignore_reprint",0);

        String jsonStr = jsonObject.toString();

        System.out.println(jsonStr);

        return SendHttp.sendPost(url,jsonStr);
    }
}
