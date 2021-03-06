package com.ebook.beans.tutoring;

import com.ebook.beans.base.BaseBean;
import com.model.utills.constants.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class Tutoring extends BaseBean  {


    @NotEmpty(message = "名称不能为空")
    private String name; //辅导名称

    @NotNull(message = "类型不能为空")
    private Integer type; //辅导类型{0：辅导，1：讲座}

    @NotNull(message = "价格不能为空")
    @Min(value = 0, message = "价格不能为空")
    private Double price; //辅导价格

    @Future(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startTime; //开始时间

    @Future(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endTime; //结束时间

    @Future(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate; //辅导截止日期

    private String place; //讲座地点

    @NotEmpty(message = "微信不能为空")
    private String weiXin; //微信

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = Constant.PHONE_NUMBER, message = "输入的手机号不合法")
    private String phone; //手机号

    private String orderUser; //接单人

    private String checkCode; //接单校验

    private Integer isScore; //是否评价了{0：没有评分过，1：已经评分过了}

    private String des; //描述
}
