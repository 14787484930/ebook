package com.ebook.beans.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private String id;
    private Date createTime;  //创建时间
    private Date updateTime;
    private String openId; //用户唯一标识
    private String weiXin; //用户微信
    private String nickname; //微信昵称你
    private String phone; //用户手机号
    private Integer dataStatus; //数据状态
    private Double score; //用户评分
    private Integer scoreNumber; //评分次数

    private Integer flag; //{0：我要购买，1：我要发布}

    /*认证所需字段*/
    @NotEmpty(message = "学号不能为空！")
    private String studNo;     //学号

    @NotEmpty(message = "密码不能为空！")
    private String password; //教务处密码

    @NotEmpty(message = "验证码不能为空！")
    private String validCode; //验证码

    @NotEmpty(message = "邮箱不能为空！")
    private String email; //用户邮箱

    @NotEmpty(message = "sessionId不能为空")
    private String url;  //学生所在学校的url地址

    /*小卖铺楼栋*/
    private int buildingNumber; //宿舍楼编号
    private String superMarketName; //超市名称
}
