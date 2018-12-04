package com.ebook.serviceImpls;

import com.ebook.beans.dictionary.DataDictionary;
import com.ebook.beans.dictionary.DataDictionaryQuery;
import com.ebook.daos.DataDictionaryDao;
import com.ebook.services.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    DataDictionaryDao datadictionaryDao;

    @Override
    public List<DataDictionary> getDataDictionary() {
        return null;
    }

    @Override
    public void save(DataDictionary dataDictionary) {

    }

    @Override
    public DataDictionary getById(DataDictionaryQuery query) {
        return null;
    }

    @Override
    public void update(DataDictionary dataDictionary) {

    }

    @Override
    public void delete(DataDictionaryQuery query) {

    }
}
