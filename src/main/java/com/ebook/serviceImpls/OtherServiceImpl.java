package com.ebook.serviceImpls;

import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.daos.OtherDao;
import com.ebook.services.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    OtherDao otherDao;

    @Override
    public List<Other> getOther() {
        return null;
    }

    @Override
    public void save(Other other) {

    }

    @Override
    public Other getById(OtherQuery query) {
        return null;
    }

    @Override
    public void update(Other other) {

    }

    @Override
    public void delete(OtherQuery query) {

    }
}
