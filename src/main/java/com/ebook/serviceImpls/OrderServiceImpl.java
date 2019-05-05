package com.ebook.serviceImpls;

import com.ebook.beans.good.Good;
import com.ebook.beans.order.Order;
import com.ebook.beans.order.OrderQuery;
import com.ebook.daos.OrderDao;
import com.ebook.services.OrderService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> getOrders(OrderQuery query) {

        List<Order> orders = orderDao.getOrders();
        return orders;
    }

    @Override
    public List<Good> getDetailed(OrderQuery query) {

        List<Good> goods = orderDao.getDetailed(query);
        return goods;
    }

    @Override
    public void saveOrder(Order order) {

        order.setId(GeneratingId.getId());

        //保存订单
        orderDao.saveOrder(order);


        //保存订单详情
        for(Good good : order.getGoods()){

            good.setOrderId(order.getId());
            orderDao.saveDetail(good);
        }


    }

    @Override
    public void saveDetail(Good good) {

    }
}
