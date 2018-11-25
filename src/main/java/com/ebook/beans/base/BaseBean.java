package com.ebook.beans.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseBean {

    public String createUser; //创建者，后续会用微信来做
    public Date createTime;  //创建时间
    public String updateUser; //修改人
    public Date updateTime; //修改时间
    public Integer dataStatus; //数据状态
}
