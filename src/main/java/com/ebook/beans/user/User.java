package com.ebook.beans.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private Date createTime;  //创建时间
    private String updateUser; //修改人
    private Date updateTime; //修改时间
    private String openId; //用户唯一标识
    private String nickname; //微信昵称你
    private Integer dataStatus; //数据状态
}
