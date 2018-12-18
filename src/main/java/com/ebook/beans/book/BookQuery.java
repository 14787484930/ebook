package com.ebook.beans.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookQuery {

    private Integer id; //图书编号
    private String bookName; //图书名称
    private Integer bookType; //图书类型
    private Date startTime; //开始日期
    private Date endTime; //结束日期
    private Double startPrice; //起始价格
    private Double endPrice; //结束价格

    /*设置一个状态参数。用于判断进入我要购买，还是我要发布界面*/
    private Integer flag; //{0：我要购买，1：我要发布}


    /*分页相关*/
    private Integer pageNumber; //当前页码
    private Integer pageSize; //页面容量
}
