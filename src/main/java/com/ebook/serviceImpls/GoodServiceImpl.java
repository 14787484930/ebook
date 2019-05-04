package com.ebook.serviceImpls;

import com.ebook.beans.good.Good;
import com.ebook.beans.good.GoodQuery;
import com.ebook.daos.GoodDao;
import com.ebook.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodDao goodDao;

    @Override
    public List<Good> getGoods(GoodQuery query) {

        List<Good> goods = goodDao.getGoods(query);
        return goods;
    }

    @Override
    public Good getById(String id) {

        Good good = goodDao.getById(id);
        return good;
    }

    @Override
    public void update(Good good) {

        goodDao.update(good);
    }

    @Override
    public void delete(GoodQuery query) {

        goodDao.delete(query);
    }

    @Override
    public void save(Good good) {

        goodDao.save(good);
    }
}
