package com.model.utills.weixin;

import com.ebook.beans.article.Article;
import com.model.utills.constants.Constant;
import com.model.utills.http.SendHttp;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl
 * @date 2019/1/17 10:14
 * @describe
 * 向微信服务器上传素材
 */
public class UploadMaterial {


    /**
     *
     * @throws IOException
     */

    /**
     *
     * @param url
     * @param file
     * @return
     * @throws Exception
     * 上传图文消息中用到的图片，返回url，url链接可以直接用来加载图片，图片格式：jpg,png，图片大小只能为1M以内
     */
    public static JSONObject uploadImg(String url,File file) throws Exception {

        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN);
        JSONObject json = uploadFile(file,url);
        return json;
    }

    /**
     *
     * @param file
     * @param url
     * @param type
     * @return
     * @throws Exception
     * 上传素材（图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式；语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式；视频（video）：10MB，支持MP4格式；缩略图（thumb）：64KB，支持JPG格式）
     */
    public static JSONObject uploadMaterialPic(File file,String url,String type) throws Exception {

        url = url.replace("ACCESS_TOKEN",Constant.ACCESSTOKEN).replace("TYPE",type);
        JSONObject json = uploadFile(file,url);
        return json;
    }

    /**
     * 上传图文消息
     */
    public static void uploadMaterialNews(File file, String url, String[] mediaIds,String[] contents) throws Exception {

        List<Article> articles = new ArrayList<Article>();



    }


    /**
     * @param file
     * @param url
     * @return
     * @throws Exception
     * http工具类（发送可上传二进制文件的请求）
     */
    public static JSONObject uploadFile(File file,String url) throws Exception{

        if(file == null){
            throw new IOException("文件不存在！");
        }

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
        //String mediaId = json.getString("media_id");

        return json;
    }

}
