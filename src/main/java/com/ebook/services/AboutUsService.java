package com.ebook.services;

import com.ebook.beans.about.AboutUs;
import com.ebook.beans.about.AboutUsQuery;

import java.util.List;

public interface AboutUsService {
    public List<AboutUs> getAboutUs();
    public void save(AboutUs aboutUs);
    public AboutUs getById(String id);
    public void update(AboutUs aboutUs);
    public void delete(AboutUsQuery query);
}
