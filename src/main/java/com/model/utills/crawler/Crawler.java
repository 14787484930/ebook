package com.model.utills.crawler;

import com.model.utills.constants.Constant;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Crawler {
    private static String viewState = "dDwyMDkyNTM5NDc2Ozs+M3iHJw9HiL+DZrsSQkdK6XN8YE0=";
    private static String ViewStateGenerator = "92719903";

    /**
     * 功能：获取response中的location
     * 参数：ip:port
     * */
    public static String GetLocation(String url) throws IOException {

        //创建一个默认的HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        try {
            //以post方式请求网页http://www.yshjava.cn
            HttpPost httppost = new HttpPost(url);
            //执行请求并获取结果
            HttpResponse responseBody = httpclient.execute(httppost);
            /*
            HttpEntity entity = responseBody.getEntity();
            GetViewState(EntityUtils.toString(entity, "UTF-8"));*/

            //返回location
            Header[] hs = responseBody.getHeaders("Location");
            if (hs.length == 0) {
                return null;
            }else {
                return hs[0].getValue();
            }
        } finally {
            // 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
            httpclient.getConnectionManager().shutdown();
        }
    }

    /**
     * 功能：登陆验证
     * 参数：Map<String, String>
     *     Map(key:Location;TextBox1;TextBox2;TextBox3;)
     *
     * */
    public static Boolean WebLogin(Map<String,String> map) throws IOException{

        //创建一个默认的HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        try {
            //以post方式请求网页
            HttpPost httppost = new HttpPost(map.get("url"));

            //添加HTTP POST参数
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
            nvps.add(new BasicNameValuePair("TextBox1", map.get("TextBox1")));
            nvps.add(new BasicNameValuePair("TextBox2", map.get("TextBox2")));
            nvps.add(new BasicNameValuePair("TextBox3", map.get("TextBox3")));
            nvps.add(new BasicNameValuePair("RadioButtonList1", "学生"));
            nvps.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", ViewStateGenerator));
            nvps.add(new BasicNameValuePair("__VIEWSTATE",viewState ));
            nvps.add(new BasicNameValuePair("Button1","" ));
            nvps.add(new BasicNameValuePair("lbLanguage=","" ));

            //将POST参数以UTF-8编码并包装成表单实体对象
            httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            //执行请求并获取结果
            HttpResponse responseBody = httpclient.execute(httppost);

            //返回location
            Header[] hs = responseBody.getHeaders("Location");

            //判断是否登陆成功
            if (hs.length != 0) {
                //分析字符串中是否匹配“xh=”,有表示登陆成功
                if(hs.toString().split("xh=").length > 0)
                    return Boolean.TRUE;
            }
        } finally {
            // 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
            httpclient.getConnectionManager().shutdown();
        }
        return Boolean.FALSE;
    }

    /**
     * 功能：获取网页上的"__VIEWSTATE"和"__VIEWSTATEGENERATOR"
     *
     */
    private static void GetViewState(String html){
        Document document = Jsoup.parse(html);
        Elements eViewStateDate = document.select("input[name]");
        if (eViewStateDate.size() > 0){
            int i;
            for(i=0; i< eViewStateDate.size(); i++){
                if(eViewStateDate.get(i).attr("name").equals("__VIEWSTATE")){
                    System.out.println(eViewStateDate.get(i).attr("Value"));
                    viewState = eViewStateDate.get(i).attr("Value");
                    System.out.println("========"+viewState);
                }
                else if(eViewStateDate.get(i).attr("name").equals("__VIEWSTATEGENERATOR")){
                    System.out.println(eViewStateDate.get(i).attr("Value"));
                    ViewStateGenerator = eViewStateDate.get(i).attr("Value");
                    System.out.println("========"+ViewStateGenerator);
                }
            }
        }
    }

    /**
     * 功能：输入验证码，测试用
     *
     * */
    private static String CheckCode(){
        System.out.print("输入");
        Scanner sc = new Scanner(System.in);
        String read = sc.nextLine();
        System.out.println("输入数据：" + read);
        return read;
    }
/*    public static void main(String[] args) throws Exception{
        String location = GetLocation(Constant.SWFU_WEB_URL);
        System.out.println(location);
        Map map = new HashMap();
        map.put("url",Constant.SWFU_WEB_URL + location);
        map.put("TextBox1","20131157060");
        map.put("TextBox2","916420");
        map.put("TextBox3",CheckCode());
        Boolean b = WebLogin(map);
        if(b == Boolean.TRUE)
            System.out.println("ffff");
    }*/
}
