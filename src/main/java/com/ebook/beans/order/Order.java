package com.ebook.beans.order;

import com.ebook.beans.base.BaseBean;
import com.ebook.beans.good.Good;
import lombok.Data;

import java.util.List;

@Data
public class Order extends BaseBean {

    private String phone; //订单联系人电话

    private String address; //订单地址

    private Integer totalNum; //订单商品总数量

    private Double totalCost; //订单总金额

    private List<Good> goods; //订单详细信息
}
