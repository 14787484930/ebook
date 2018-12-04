package com.ebook.beans.log;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class Log implements Serializable {

    @NotEmpty(message = "日志编号不能为空")
    private  String id;

    @NotEmpty(message = "微信号不能为空")
    private String weixin;

    private String describe;

    @NotNull(message = "日期不能为空")
    @Past(message = "出版日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
}
