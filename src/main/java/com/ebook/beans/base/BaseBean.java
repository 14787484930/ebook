package com.ebook.beans.base;

import com.ebook.beans.user.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseBean implements Serializable {

    public String id;       //图书编号
    public User createUser; //创建者，后续会用微信来做
    public Date createTime;  //创建时间
    public User updateUser; //修改人
    public Date updateTime; //修改时间
    public Integer viewTimes; //浏览次数
    public Integer dataStatus; //数据状态 (1:正常，100；删除)
}
