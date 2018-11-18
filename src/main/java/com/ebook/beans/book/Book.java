package com.ebook.beans.book;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class Book implements Serializable {
    private Integer id;

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
    private String pic;
}
