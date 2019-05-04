package com.ebook.services;

import com.ebook.beans.good.Good;
import com.ebook.beans.good.GoodQuery;

import java.util.List;

public interface GoodService {

    //查看商品列表
    public List<Good> getGoods(GoodQuery query);

    //查看商品详细信息
    public Good getById(String id);

    //编辑商品信息
    public void update(Good good);

    //删除商品信息
    public void delete(GoodQuery query);

    //新增商品信息
    public void save(Good good);
}
