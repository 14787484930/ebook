package com.model.utills.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SendHttp {

    public static String sengGet(String url) {

        /*使用HttpClient发送请求,3步*/
        //获取执行对象
        HttpClient httpClient = HttpClients.createDefault();
        //创建请求对象
        HttpGet httpGet = new HttpGet(url);
        //发送请求，获取响应对象
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            /*解析响应结果*/
            //得到响应状态
            int code = response.getStatusLine().getStatusCode();
            System.out.println("=========="+code);
            //得到响应内容
            HttpEntity httpEntity = response.getEntity();
            String jsonStr = EntityUtils.toString(httpEntity);
            System.out.println(jsonStr);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*{
            "access_token":"16_U8j-vvdjs3-M-IS1OFwxHty6nVAiCK95SMx0lOxm5ETo4G5S8L1JuRt42H_peD1IoMaSP85SINqGU7gSiSV7xIthP-eKP6jJThZ5Vc4qgDqmYiyMHGAIinvqg2nTw2V7OEl6N7RTmWIxatRLVJCcABAGUN",
            "expires_in":7200
        }*/
    }

    /**
     * zxl
     * 自定义菜单
     * */
    public static String sendPost(String url,String paramsJson){

        /*发送post请求*/
        //创建执行对象
        HttpClient httpClient = HttpClients.createDefault();
        //创建发送对象
        HttpPost httpPost = new HttpPost(url);
        try {
            //设置请求参数
            httpPost.setEntity(new StringEntity(paramsJson,"UTF-8"));
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
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }


    }
}
