package com.ebook.serviceImpls;

import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;
import com.ebook.daos.ElectronicsDao;
import com.ebook.services.ElectronicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectronicsServiceImpl implements ElectronicsService {

    @Autowired
    ElectronicsDao electronicsDao;

    @Override
    public List<Electronics> getElectronics() {
        return null;
    }

    @Override
    public void save(Electronics electronics) {

    }

    @Override
    public Electronics getById(ElectronicsQuery query) {
        return null;
    }

    @Override
    public void update(Electronics electronics) {

    }

    @Override
    public void delete(ElectronicsQuery query) {

    }
}
