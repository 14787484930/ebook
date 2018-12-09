package com.model.utills.parse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * zxl
 * 2018-12-09
 * 解析微信用户发过来的xml文件
 */
public class ParseXML {

    public static Map<String,String> parse(Reader reader){

        Map<String,String> result = new HashMap<String,String>();

        try {
            //创建解析器
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            //设置解析内容
            parser.setInput(reader);

            //获取当前事件类型
            int eventType = parser.getEventType();

            //基于事件做解析
            //XmlPullParser.START_DOCUMENT 文档开始
            //XmlPullParser.END_DOCUMENT文档结束
            //XmlPullParser.START_TAG 标签开始
            //XmlPullParser.END_TAG 标签结束

            //只有eventType=END_DOCUMENT说明解析结束。
            while(XmlPullParser.END_DOCUMENT != eventType){

                String targetName = parser.getName();
                if(XmlPullParser.START_TAG == eventType && !targetName.equals("xml")){
                    String targetContext = parser.nextText();
                    result.put(targetName,targetContext);
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {

        String content = "<xml>"
                + "<ToUserName><![CDATA[gh_ae9b8f50b1b3]]></ToUserName>"
                + "<FromUserName><![CDATA[oxLXms99NViH1QDw_mLZ7k8nCfdc]]></FromUserName>"
                + "<CreateTime>1488349732</CreateTime>"
                + "<MsgType><![CDATA[text]]></MsgType>"
                + "<Content><![CDATA[哈喽]]></Content>"
                + "<MsgId>6392413424506318646</MsgId>"
                + "</xml>";
        Map<String,String> map = parse(new StringReader(content));

        Set<Entry<String,String>> entrySet = map.entrySet();
        Iterator<Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Entry<String,String> next = iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }

    }

}
