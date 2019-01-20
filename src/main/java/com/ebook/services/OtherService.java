package com.ebook.services;

import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;

import java.util.List;

public interface OtherService {

    public List<Other> getOthers(OtherQuery query);
    public void save(Other other);
    public Other getById(String id);
    public void update(Other other);
    public void delete(OtherQuery query);
}
