package com.model.utills.weixin;

import com.ebook.container.WeiXinToken;
import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;

/**
 * @author zxl
 * @date 2019/4/18 13:53
 * @describe 微信接口调用凭据AccessToken操作工具
 */
public class AccessTokenUtill {

    public static boolean getAccessToken(){

        String url = Constant.GET_ACCESS_TOKEN_URL.replace("APPID",Constant.APPID).replace("APPSECRET",Constant.APPSECRET);

        String accessTokenJson = SendHttp.sendGet(url);

        if(accessTokenJson != null){

            JSONObject json = JSONObject.fromObject(accessTokenJson);
            WeiXinToken.ACCESSTOKEN = json.getString("access_token");
            return true;
        }else{
            return false;
        }

        /*{
            "access_token":"16_U8j-vvdjs3-M-IS1OFwxHty6nVAiCK95SMx0lOxm5ETo4G5S8L1JuRt42H_peD1IoMaSP85SINqGU7gSiSV7xIthP-eKP6jJThZ5Vc4qgDqmYiyMHGAIinvqg2nTw2V7OEl6N7RTmWIxatRLVJCcABAGUN",
            "expires_in":7200
        }*/

    }
}
