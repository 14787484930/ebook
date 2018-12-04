package com.ebook.beans.electronics;

import com.ebook.beans.base.BaseBean;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class Electronics extends BaseBean implements Serializable {
    private String id;

    @NotEmpty(message = "产品名称不能为空")
    private String elecName;

    @NotNull(message = "原价不能为空")
    @Min(value = 0, message = "最小值为0")
    private Double originalPrice;

    @NotNull(message = "现价不能为空")
    @Min(value = 0, message = "最小值为0")
    private Double presentPrice;

    @NotNull(message = "购买日期不能为空")
    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date buyDate;

    @NotNull(message = "是否有发票不能为空(0-没有 1-有)")
    private Integer hasInvoice;

    @NotEmpty(message = "图片不能为空")
    private String elecPic;

    @NotEmpty(message = "微信不能为空")
    private String weiXin;

    @NotEmpty(message = "手机不能为空")
    private String phone;

    private String describe;

    private Integer viewTime;

}
