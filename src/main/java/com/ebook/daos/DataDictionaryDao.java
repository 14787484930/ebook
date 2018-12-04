package com.ebook.daos;

import com.ebook.beans.dictionary.DataDictionary;
import com.ebook.beans.dictionary.DataDictionaryQuery;

import java.util.List;

public interface DataDictionaryDao {

    public List<DataDictionary> getDataDictionary();
    public void save(DataDictionary dataDictionary);
    public DataDictionary getById(DataDictionaryQuery query);
    public void update(DataDictionary dataDictionary);
    public void delete(DataDictionaryQuery query);
}
