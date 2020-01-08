import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import com.model.utills.weixin.AccessTokenUtill;
import com.model.utills.weixin.MenuUtill;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


/**
 * @author zxl
 * @date 2018/12/11 14:14
 * @describe
 */
public class WeiXinSend {

    /**
     * zxl
     * 获取接口调用凭据（因为所有的接口调用都要用到这个凭据）
     * */
    @Test
    public void doGet() throws IOException {

        String url = Constant.GET_ACCESS_TOKEN_URL.replace("APPID",Constant.APPID).replace("APPSECRET",Constant.APPSECRET);

        /*使用HttpClient发送请求,3步*/
        //获取执行对象
        HttpClient httpClient = HttpClients.createDefault();
        //创建请求对象
        HttpGet httpGet = new HttpGet(url);
        //发送请求，获取响应对象
        HttpResponse response = httpClient.execute(httpGet);

        /*解析响应结果*/
        //得到响应状态
        int code = response.getStatusLine().getStatusCode();
        System.out.println("=========="+code);
        //得到响应内容
        HttpEntity httpEntity = response.getEntity();
        String jsonStr = EntityUtils.toString(httpEntity);
        System.out.println(jsonStr);

        /*{
            "access_token":"16_U8j-vvdjs3-M-IS1OFwxHty6nVAiCK95SMx0lOxm5ETo4G5S8L1JuRt42H_peD1IoMaSP85SINqGU7gSiSV7xIthP-eKP6jJThZ5Vc4qgDqmYiyMHGAIinvqg2nTw2V7OEl6N7RTmWIxatRLVJCcABAGUN",
            "expires_in":7200
        }*/

    }


    /**
     * zxl
     * 添加菜单
     * */
    @Test
    public void add(){

        String url = Constant.ADD_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        String jsonParams = objToJsonstr();
        System.out.println(jsonParams);

        String jsonStr = SendHttp.sendPost(url,jsonParams);

        System.out.println(jsonStr);
    }

    /**
     * zxl
     * 拼接菜单
     * */
    public static String objToJsonstr(){

        //根菜单
        JSONObject root = new JSONObject();

        //一级菜单集合
        JSONArray oneLevelMenus = new JSONArray();

        //*一级菜单*//*
        //进入、广告、关于我们
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
        toBuy.put("url","https://8733bedd.ngrok.io/weixin/getNumber");

        //我要发布
        JSONObject toRealse = new JSONObject();
        toRealse.put("type","view");
        toRealse.put("name","我要发布");
        toRealse.put("url","https://8733bedd.ngrok.io/");

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
        introduceOur.put("url","http://www.baidu.com");

        //我要发布
        JSONObject myAdvice = new JSONObject();
        myAdvice.put("type","view");
        myAdvice.put("name","关于我们");
        myAdvice.put("url","https://www.taobao.com");

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

    /**
     * zxl
     * 查询自定义菜单
     * */
    @Test
    public void getMenu(){

        String url = Constant.GET_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        String menuStr = SendHttp.sendGet(url);

        System.out.println(menuStr);

    }

    /**
     * zxl
     * 删除菜单
     * */
    @Test
    public void delMenu(){

        String url = Constant.DEL_MENU_URL.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);
        String str = SendHttp.sendGet(url);
        System.out.println(str);
    }

    @Test
    public void getUserMassage(){

        String str = "16_BsC9tqc_bM1Ath0YKEZDZgabQG4wDE1iZcCdZOY2uNN3bilRvKLQ8gwIYIqFJTxFnG9w8a_Fq8q2vyRFppLvaTXtt30nPiL7J31B9LKISP6HrSPe94V_1tCBZ3m71YM5dU5gCBw16S1Gi7G9TSFeABAONA";
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN",str);
        url = url.replace("OPENID","og4jG1LVi3tcqeWIU_Dw4c1SdfGk");

        String s = SendHttp.sendGet(url);
        System.out.println(s);

    }

    @Test
    public void abc(){

        AccessTokenUtill.getAccessToken();

        //MenuUtill.delMenu();
        MenuUtill.addMenu();
    }

}
