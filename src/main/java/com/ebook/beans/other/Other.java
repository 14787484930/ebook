package com.ebook.beans.other;

import com.ebook.beans.base.BaseBean;
import com.model.utills.constants.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class Other extends BaseBean {

    @NotEmpty(message = "产品名称不能为空")
    private String otherName; //产品名称

    @NotNull(message = "购买日期不能为空")
    @Past(message = "日期不合法")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate; //购买时间

    @NotNull(message = "原价不能为空")
    @Min(value = 0, message = "原价不能小于0")
    private Double originalPrice; //原价

    @NotNull(message = "现价不能为空")
    @Min(value = 0, message = "最小值为0")
    private Double presentPrice; //现价

    @NotNull(message = "是否有发票不能为空")
    //@Pattern(regexp = "0|1",message = "是否有发票的值不合法")
    private Integer hasInvoice; //是否有发票不能为空(0-没有 1-有)

    private String otherPic; //产品图片

    // @NotEmpty(message = "微信不能为空")
    private String weiXin; //微信

    @NotNull(message = "手机不能为空")
    @Pattern(regexp = Constant.PHONE_NUMBER, message = "输入的手机号不合法")
    private String phone; //手机号

    private String des; //产品描述

}
