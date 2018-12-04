package com.ebook.beans.about;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserAdvice implements Serializable {
    private String id;        //用户建议编号

    @NotEmpty(message = "建议内容不能为空")
    private String describe;  //建议内容

    private String createUser;//创建人

    private Date createTime;  //创建日期
}
