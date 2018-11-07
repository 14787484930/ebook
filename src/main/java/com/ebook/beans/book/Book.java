package com.ebook.beans.book;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Book implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Date pubDate;
    private String pic;
}
