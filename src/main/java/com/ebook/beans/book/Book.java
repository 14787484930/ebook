package com.ebook.beans.book;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer id;
    private String name;
    private Double price;
    private Date pubDate;
    private String pic;
}
