package com.ebook.beans.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    public Date createTime;  //创建时间
    public String updateUser; //修改人
    public Date updateTime; //修改时间
    public Integer dataStatus; //数据状态
}
