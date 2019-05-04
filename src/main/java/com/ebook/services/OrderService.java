package com.ebook.services;

import com.ebook.beans.good.Good;
import com.ebook.beans.order.Order;
import com.ebook.beans.order.OrderQuery;

import java.util.List;

public interface OrderService {

    //查看订单信息
    public List<Order> getOrders();

    //查看订单详细信息
    public List<Good> getDetailed(OrderQuery query);

    //添加订单
    public void saveOrder(Order order);

    //添加订单详细信息
    public void saveDetail(Good good);
}
