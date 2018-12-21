package com.ebook.daos;

import com.ebook.beans.electronicstype.ElectronicsType;
import com.ebook.beans.electronicstype.ElectronicsTypeQuery;

import java.util.List;

public interface ElectronicsTypeDao {
    public List<ElectronicsType> getElectronicsType(ElectronicsTypeQuery query);
    public void save(ElectronicsType electronicsType);
    public ElectronicsType getById(String id);
    public void update(ElectronicsType electronics);
    public void delete(ElectronicsTypeQuery query);
}
