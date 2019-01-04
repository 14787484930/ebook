package com.ebook.beans.about;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class AboutUs implements Serializable {
    private String id;

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "内容描述不能为空")
    private String des;
}
