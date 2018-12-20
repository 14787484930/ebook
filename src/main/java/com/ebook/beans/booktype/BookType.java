package com.ebook.beans.booktype;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zxl
 * @date 2018/12/20 11:30
 * @describe
 */
@Data
public class BookType implements Serializable {

    private String id;
    private String name;
    private String des;

}
