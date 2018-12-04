package com.ebook.services;

import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;

import java.util.List;

public interface ElectronicsService {

    public List<Electronics> getElectronics();
    public void save(Electronics electronics);
    public Electronics getById(ElectronicsQuery query);
    public void update(Electronics electronics);
    public void delete(ElectronicsQuery query);
}
