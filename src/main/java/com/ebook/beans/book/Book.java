package com.ebook.beans.book;

import com.ebook.beans.base.BaseBean;
import com.ebook.beans.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class Book extends BaseBean implements Serializable {

    /*private Integer id;

    @NotEmpty(message = "书名不能为空")
    private String name;

    @NotNull(message = "价格不能为空")
    @Min(value = 0, message = "的最小值为0")
    private Double price;

    @NotNull(message = "日期不能为空")
    @Past(message = "出版日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pubDate;

    @NotEmpty(message = "图片不能为空")
    private String pic;*/

    private String id;                  //图书编号

    @NotEmpty(message = "图书名称不能为空")
    private String bookName;           //图书名称

    private Integer bookType;          //图书类型

    @NotEmpty(message = "作者不能为空")
    private String author;             //作者

    @NotNull(message = "价格不能为空")
    private Double bookPrice;         //价格

    @NotNull(message = "日期不能为空")
    @Past(message = "出版日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pubDate;             //出版日期

    @NotNull(message = "出版社不能为空")
    private String bookPub;           //出版社

    private String bookPic;           //图书图片

    @NotNull(message = "微信不能为空")
    private String weiXin;            //微信

    @NotNull(message = "手机不能为空")
    private String phone;             //电话

    private String describe;          //描述

}
