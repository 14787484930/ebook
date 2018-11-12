package com.model.utills.messages;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * zxl
 * 2018-11-12
 * 客户端请求返回结果封装类
 */
@Data
public class ResultInfo {

    //返回状态码{100：SUCCESS，200：fail}
    private Integer code;

    //异常等提示信息
    private Map<String,Object> msgs = new HashMap<String,Object>();

    //pageInfo数据（回馈用户的主要数据）
    private  Map<String,Object> pageinfo = new HashMap<String,Object>();


    public static ResultInfo success(){
        ResultInfo result = new ResultInfo();
        result.setCode(100);
        result.msgs.put("msg","处理成功！");
        return result;
    }

    public static ResultInfo fail(){
        ResultInfo result = new ResultInfo();
        result.setCode(200);
        result.msgs.put("msg","处理失败！");
        return result;
    }

    public ResultInfo add(String key,Object value){
        this.pageinfo.put(key, value);
        return this;
    }
}
