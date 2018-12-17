package com.ebook.beans.book;

import lombok.Data;

@Data
public class BookQuery {

    private Integer id; //图书编号
    private String bookName; //图书名称


    /*分页相关*/
    private Integer pageNumber; //当前页码
    private Integer pageSize; //页面容量
}
