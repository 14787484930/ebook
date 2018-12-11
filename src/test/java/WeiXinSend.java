import com.model.utills.constants.Constant;
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

    }

    @Test
    public void doPost() throws IOException {

        /*发送post请求*/
        //创建执行对象
        HttpClient httpClient = HttpClients.createDefault();
        //创建发送对象
        HttpPost httpPost = new HttpPost();
        //设置请求参数
        //httpPost.setEntity();
        //发送请求，得到响应结果
        HttpResponse response = httpClient.execute(httpPost);

        /*解析响应结果*/
        //得到请求状态
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);
        //得到响应内容
        HttpEntity httpEntity = response.getEntity();
        String jsonStr = EntityUtils.toString(httpEntity);
        System.out.println(jsonStr);
    }
}
