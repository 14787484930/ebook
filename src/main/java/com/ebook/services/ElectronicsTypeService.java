package com.ebook.services;

import com.ebook.beans.electronicstype.ElectronicsType;
import com.ebook.beans.electronicstype.ElectronicsTypeQuery;

import java.util.List;

public interface ElectronicsTypeService {
    public List<ElectronicsType> getElectronicsType(ElectronicsTypeQuery query);
    public void save(ElectronicsType electronicsType);
    public ElectronicsType getById(String id);
    public void update(ElectronicsType electronicsType);
    public void delete(ElectronicsTypeQuery query);
}
