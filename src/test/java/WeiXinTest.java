import com.fasterxml.jackson.core.JsonParser;
import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import com.model.utills.uuid.GeneratingId;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.content.image.png;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zxl
 * @date 2019/1/10 14:32
 * @describe
 */
public class WeiXinTest {


    /**
     * 上传图文消息中用到的图片，返回url，url链接可以直接用来加载图片
     * 图片格式：jpg,png
     * 图片大小只能为1M以内
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {

        //请求链接
        String url1 = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        url1 = url1.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        URL url = new URL(url1);
        String result = null;
        File file = new File("F:/pic","1.jpg");

        if (!file.exists() || !file.isFile()) {
            throw new IOException("上传的文件不存在");
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                + BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        // JSONObject jsonObj = new JSONObject(result);
        // return jsonObj;
        /*JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
        JsonObject json = jsonparer.parse(result).getAsJsonObject();*/
        System.out.println(result);
    }

    @Test
    public void pictest(){

        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        //图片url
        //String str = "http://mmbiz.qpic.cn/mmbiz_jpg/6sGemdgERQk7wosk0NI32ibibMCOO440O536ltKII2murU2FBqibvpsibwMMwU9cC6ryzSWYCPCSRWpHELHds70UrA/0";
    }

    /**
     * 上传临时素材（图片）
     */
    @Test
    public void test(){

        //请求连接和accesstonken的获取
        String url1 = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        url1 = url1.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        try {
            String media_id = uploadFile("F:/pic/3.jpg",Constant.ACCESSTOKEN,"image");
            System.out.println(media_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 返回结果
         * {
         * 	"type": "image",
         * 	"media_id": "TfsARwNriFKsPBPEfW-H7HLVPEotCKhNFAQjKH0tGekyLC3j5IPkgUWMqwauOOs_",
         * 	"created_at": 1547542396
         * }
         *
         *{
         * 	"type": "image",
         * 	"media_id": "7yJFrNCiAG5snIKYFYPwiAypSvPdd9BhBiIhb-ddvMDB26MKN7GHLD2tbP6oTDzG",
         * 	"created_at": 1547565519
         * }
         *
         *{
         * 	"type": "image",
         * 	"media_id": "auy7p7hCnYtW3bisSIEkPhVG9Q0lKc_fjmVoTB-U_Jau7qdhWgp0qn0_x2lg-YIC",
         * 	"created_at": 1547566313
         * }
         *
         */
    }

    /**
     * 上传图文消息
     */
    @Test
    public void tuwen(){

        //请求接口的url
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        System.out.println(url);

        //初始化图文消息
        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        String[] mediaIds = {"TfsARwNriFKsPBPEfW-H7HLVPEotCKhNFAQjKH0tGekyLC3j5IPkgUWMqwauOOs_",
                "7yJFrNCiAG5snIKYFYPwiAypSvPdd9BhBiIhb-ddvMDB26MKN7GHLD2tbP6oTDzG",
                "auy7p7hCnYtW3bisSIEkPhVG9Q0lKc_fjmVoTB-U_Jau7qdhWgp0qn0_x2lg-YIC"};

        /*article.setAuthor("zxl");
        article.setTitle("第一个图文消息");*/

        StringBuffer sb = new StringBuffer();
        sb.append("<h1>希望大家齐心协力</h1>").append("<p style=\"color:green\">").
                append("前几天看到一个小区的电梯和墙上搞广告的收入给小区居民发了好多钱，说明广告的收入是可观的，希望大家是发自内心的去做，一起加油。</p>").append("<br/><img src=\"http://mmbiz.qpic.cn/mmbiz_jpg/6sGemdgERQk7wosk0NI32ibibMCOO440O536ltKII2murU2FBqibvpsibwMMwU9cC6ryzSWYCPCSRWpHELHds70UrA/0\"");

        for(int i = 0;i < 3;i++){

            article = new Article();
            article.setAuthor("zxl");
            article.setTitle("第一个图文消息");
            article.setThumb_media_id(mediaIds[i]);
            if(i == 1){
                article.setTitle("什么叫全力以赴做一件事");
            }else if(i == 2){
                article.setTitle("既然选择了出发就不要徘徊不前");
            }else{
                article.setTitle("一起加油，但不勉强，也不强求");
            }
            article.setContent(sb.toString());
            articles.add(article);
        }

        JSONObject jsonStr = new JSONObject();

        //将list转成JsonArray传给工具类
        JSONArray articlesStr =  JSONArray.fromObject(articles);
        jsonStr.put("articles",articlesStr);

        String scStr = jsonStr.toString();

        System.out.println(scStr);

        String resutl = SendHttp.sendPost(url,scStr);
        System.out.println(resutl);
        /**
         * 得到的结果
         * {
         * 	"type": "news",
         * 	"media_id": "1TA15BQ4slN5fKJKZW-e3a6B2L1EboSgK_vSkbSyvYKhixMCpVYwC2H_FHR-Ep5D",
         * 	"created_at": 1547603802
         * }
         *
         * {
         * 	"type": "news",
         * 	"media_id": "T9w6S113LMOVzOL1xkT2y4pwMdsP3t_3t5FWX_BBAbdMl-p4dxIZTJueildeX_-9",
         * 	"created_at": 1547626354
         * }
         *
         * {
         * 	"type": "news",
         * 	"media_id": "R-xtO27T3WyPTeWjTjGRnEwJdl4YQSAUfzApIvl3PSsOqgJgQGJMg7-VLcaExWgB",
         * 	"created_at": 1547627004
         * }
         *
         */
    }

    /**
     * 群发图文消息
     *
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "mpnews":{z
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"mpnews",
     *     "send_ignore_reprint":0
     * }
     */
    @Test
    public void sendMessageAll(){

        //String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
        //预览接口
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        JSONObject jsonObject = new JSONObject();

        //filter
        JSONObject jsonFilter = new JSONObject();
        jsonFilter.put("is_to_all",true);
        //jsonFilter.put("tag_id",2);

        //jsonObject.put("filter",jsonFilter);

        String[] openids = {"og4jG1LVi3tcqeWIU_Dw4c1SdfGk","og4jG1Di02DF-sel9Tker8aJue-s","og4jG1PAHCx2OvTAbtCN3nLS-ZJ8","og4jG1LuCIuwtvTPPzrSN6wTbSv4","og4jG1Jtwh11ke6GKZudLGHcEGho"};
        List<String> list = new ArrayList<>();
        for(String str : openids){

            list.add(str);
        }

//        jsonObject.put("touser",JSONArray.fromObject(openids));
        jsonObject.put("touser","og4jG1LVi3tcqeWIU_Dw4c1SdfGk");

        //mpnews
        JSONObject jsonMpnews = new JSONObject();
        jsonMpnews.put("media_id","tobhyZkRIifne9JtlvhJrRbi4_DLnS67hBK2i2gny81CFEN7x2Nz2je_dEVqSt2z");

        jsonObject.put("mpnews",jsonMpnews);

        //msgtype
        jsonObject.put("msgtype","mpnews");

        //send_ignore_reprint
        jsonObject.put("send_ignore_reprint",0);

        String jsonStr = jsonObject.toString();

        System.out.println(jsonStr);

        for(int i=0;i<openids.length;i++){

            jsonObject.put("touser",openids[i]);
            String result = SendHttp.sendPost(url,jsonStr);
        }

        //String result = SendHttp.sendPost(url,jsonStr);

        //System.out.println(result);

    }

    /**
     * 图文消息中图片上传
     */
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static String uploadFile(String filePath, String accessToken, String type) throws Exception{
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在！");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        URL urlObj = new URL(url);

        //连接
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        //请求头
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //conn.setRequestProperty("Content-Type","multipart/form-data;");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //输出流
        OutputStream out = new DataOutputStream(conn.getOutputStream());

        out.write(head);

        //文件正文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while((bytes = in.read(bufferOut))!=-1) {
            out.write(bufferOut,0,bytes);
        }
        in.close();

        //结尾
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
        out.write(foot);
        out.flush();
        out.close();

        //获取响应
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        while((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        if(result == null) {
            result = buffer.toString();
        }
        reader.close();

        //需要添加json-lib  jar包
        JSONObject json = JSONObject.fromObject(result);
        System.out.println(json);
        String mediaId = json.getString("media_id");

        return mediaId;
    }


}