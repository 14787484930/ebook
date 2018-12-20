package com.ebook.beans.book;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Data
public class BookQuery extends BaseQuery {

    private String bookName; //图书名称
    private Integer bookType; //图书类型
}
