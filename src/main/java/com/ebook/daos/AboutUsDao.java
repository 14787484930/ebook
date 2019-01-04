package com.ebook.daos;

import com.ebook.beans.about.AboutUs;
import com.ebook.beans.about.AboutUsQuery;

import java.util.List;

public interface AboutUsDao {
    public List<AboutUs> getAboutUs();
//    public void save(AboutUs aboutUs);
    public AboutUs getById(String id);
//    public void update(AboutUs aboutUs);
//    public void delete(AboutUsQuery query);
}
