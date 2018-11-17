package com.ebook.beans.book;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Book implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pubDate;
    private String pic;
}
