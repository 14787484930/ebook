package com.ebook.daos;

import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;

import java.util.List;

public interface ElectronicsDao {
    public List<Electronics> getElectronics(ElectronicsQuery query);
    public void save(Electronics electronics);
    public Electronics getById(String id);
    public void update(Electronics electronics);
    public void delete(ElectronicsQuery query);
    public void updateWarning(ElectronicsQuery query); //系统标记预警信息
    public void updateDel(ElectronicsQuery query); //管理员处理预警信息
}
