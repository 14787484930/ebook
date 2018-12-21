package com.ebook.serviceImpls;

import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.daos.OtherDao;
import com.ebook.services.OtherService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    OtherDao otherDao;

    @Override
    public List<Other> getOthers() {

        return otherDao.getOthers();
    }

    @Override
    public void save(Other other) {

        //生成主键
        other.setId(GeneratingId.getId());
        //保存产品
        otherDao.save(other);
    }

    @Override
    public Other getById(String id) {

        return otherDao.getById(id);
    }

    @Override
    public void update(Other other) {

        otherDao.update(other);
    }

    @Override
    public void delete(OtherQuery query) {

        otherDao.delete(query);
    }
}
