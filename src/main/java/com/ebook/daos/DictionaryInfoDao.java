package com.ebook.daos;

import com.ebook.beans.dictionary.DictionaryInfo;
import com.ebook.beans.dictionary.DictionaryInfoQuery;

import java.util.List;

public interface DictionaryInfoDao {

    public List<DictionaryInfo> getDictionaryInfo();
    public void save(DictionaryInfo dictionaryInfo);
    public DictionaryInfo getById(DictionaryInfoQuery query);
    public void update(DictionaryInfo dictionaryInfo);
    public void delete(DictionaryInfoQuery query);
}
