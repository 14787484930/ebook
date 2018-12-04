package com.ebook.serviceImpls;

import com.ebook.beans.dictionary.DictionaryInfo;
import com.ebook.beans.dictionary.DictionaryInfoQuery;
import com.ebook.daos.DictionaryInfoDao;
import com.ebook.services.DictionaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryInfoServiceImpl implements DictionaryInfoService {
    @Autowired
    DictionaryInfoDao dictionaryinfoDao;

    @Override
    public List<DictionaryInfo> getDictionaryInfo() {
        return null;
    }

    @Override
    public void save(DictionaryInfo dictionaryInfo) {

    }

    @Override
    public DictionaryInfo getById(DictionaryInfoQuery query) {
        return null;
    }

    @Override
    public void update(DictionaryInfo dictionaryInfo) {

    }

    @Override
    public void delete(DictionaryInfoQuery query) {

    }
}
