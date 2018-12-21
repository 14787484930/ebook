package com.ebook.beans.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private String id;
    private Date createTime;  //创建时间
    private Date updateTime;
    private String studNo;     //学号
    private String openId; //用户唯一标识
    private String weiXin; //用户微信
    private String nickname; //微信昵称你
    private String email; //用户邮箱
    private String phone; //用户手机号
    private Integer dataStatus; //数据状态
}
