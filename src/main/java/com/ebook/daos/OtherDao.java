package com.ebook.daos;

import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;

import java.util.List;

public interface OtherDao {

    public List<Other> getOthers(OtherQuery query);
    public void save(Other other);
    public Other getById(String id);
    public void update(Other other);
    public void delete(OtherQuery query);

    public void updateWarning(OtherQuery query); //系统标记预警信息
    public void updateDel(OtherQuery query); //管理员处理预警信息
    public void updateViews(OtherQuery query); //浏览量的统计
}
