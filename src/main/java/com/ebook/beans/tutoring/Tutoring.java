package com.ebook.beans.tutoring;

import com.ebook.beans.base.BaseBean;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class Tutoring extends BaseBean implements Serializable {
    @NotEmpty(message = "辅导编号不能为空")
    private String id;

    @NotEmpty(message = "辅导名不能为空")
    private String name;

    @NotNull(message = "辅导类型不能为空")
    private Integer type;

    @NotNull(message = "价格不能为空")
    @Min(value = 0, message = "价格不能为空")
    private Double price;

    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String place;

    @NotEmpty(message = "微信不能为空")
    private String weiXin;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    private String describe;
    // private Integer viewTime;
}
