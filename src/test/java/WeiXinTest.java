import com.fasterxml.jackson.core.JsonParser;
import com.model.utills.constants.Constant;
import com.model.utills.uuid.GeneratingId;
import net.sf.json.JSON;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zxl
 * @date 2019/1/10 14:32
 * @describe
 */
public class WeiXinTest {

    @Test
    public void wxDemo() {

        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);

        Map<String, String> resultMap = new HashMap<String, String>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";

        File file = new File("F:/pic","123.png");

        /*FileInputStream fileInputStream = new FileInputStream(file1);
        MultipartFile file = new MockMultipartFile(file1.getName(), file1.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);*/

        //System.out.println(file.getName());


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "multipart/form-data; boundary=");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //File file = new File("F:/pic", GeneratingId.getId() + "." + endStr);

            builder.addBinaryBody(file.getName(), file, ContentType.DEFAULT_BINARY, file.getName());// 文件流

            //builder.addTextBody("filename",file.getName());
            //builder.addTextBody("filelength",file.length()+"");
            //builder.addTextBody("content-type","application/octet-stream");



            //决中文乱码
            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交

            HttpEntity responseEntity = response.getEntity();
            resultMap.put("scode", String.valueOf(response.getStatusLine().getStatusCode()));
            resultMap.put("data", "");
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
                resultMap.put("data", result);
            }
            System.out.println(result);
        } catch (Exception e) {
            resultMap.put("scode", "error");
            resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());

            Writer w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
    public void test(){

        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        String str = "http://mmbiz.qpic.cn/mmbiz_jpg/6sGemdgERQk7wosk0NI32ibibMCOO440O536ltKII2murU2FBqibvpsibwMMwU9cC6ryzSWYCPCSRWpHELHds70UrA/0";

    }


}