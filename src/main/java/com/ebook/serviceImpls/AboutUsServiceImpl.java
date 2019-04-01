package com.ebook.serviceImpls;

import com.ebook.beans.about.AboutUs;
import com.ebook.beans.about.AboutUsQuery;
import com.ebook.daos.AboutUsDao;
import com.ebook.services.AboutUsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    AboutUsDao aboutUsDao;

    @Override
    public AboutUs getAboutUs() {
        AboutUs aboutUs = aboutUsDao.getAboutUs();
        return aboutUs;
    }

    @Override
    public void save(AboutUs aboutUs) {

    }

    @Override
    public AboutUs getById(String id) {

        return aboutUsDao.getById(id);
    }

    @Override
    public void update(AboutUs aboutUs) {

    }

    @Override
    public void delete(AboutUsQuery query) {

    }
}
