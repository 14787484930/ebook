package com.ebook.daos;

import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;

import java.util.List;

public interface OtherDao {

    public List<Other> getOther();
    public void save(Other other);
    public Other getById(OtherQuery query);
    public void update(Other other);
    public void delete(OtherQuery query);
}
