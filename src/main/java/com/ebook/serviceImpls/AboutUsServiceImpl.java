package com.ebook.serviceImpls;

import com.ebook.beans.about.AboutUs;
import com.ebook.beans.about.AboutUsQuery;
import com.ebook.services.AboutUsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    AboutUsService aboutUsService;

    @Override
    public List<AboutUs> getAboutUs() {
        return null;
    }

    @Override
    public void save(AboutUs aboutUs) {

    }

    @Override
    public AboutUs getById(AboutUsQuery query) {
        return null;
    }

    @Override
    public void update(AboutUs aboutUs) {

    }

    @Override
    public void delete(AboutUsQuery query) {

    }
}
