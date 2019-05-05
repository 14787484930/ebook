package com.ebook.beans.good;

import com.ebook.beans.base.BaseBean;
import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author zxl
 * @date 2019/4/29 9:36
 * @describe
 */
@Data
public class Good extends BaseBean {

    @NotNull(message = "商品名称不能为空！")
    private String goodName; //商品名称

    @NotNull(message = "价格不能为空！")
    @Min(value = 0, message = "最小值为0")
    private double goodPrice; //商品价格

    private String goodPic; //商品图片


    private String des; //描述

    private Integer buyNumber; //购买数量

    private String orderId; //订单编号


}
