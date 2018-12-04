package com.ebook.services;

import com.ebook.beans.dictionary.DataDictionary;
import com.ebook.beans.dictionary.DataDictionaryQuery;

import java.util.List;

public interface DataDictionaryService {

    public List<DataDictionary> getDataDictionary();
    public void save(DataDictionary dataDictionary);
    public DataDictionary getById(DataDictionaryQuery query);
    public void update(DataDictionary dataDictionary);
    public void delete(DataDictionaryQuery query);
}
