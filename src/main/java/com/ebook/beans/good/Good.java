package com.ebook.beans.good;

import com.ebook.beans.base.BaseBean;
import lombok.Data;

/**
 * @author zxl
 * @date 2019/4/29 9:36
 * @describe
 */
@Data
public class Good extends BaseBean {

    private String goodName; //商品名称
    private double goodPrice; //商品价格
    private String goodPic; //商品图片
    private String des; //描述

    private Integer buyNumber; //购买数量
    private String orderId; //订单编号


}
