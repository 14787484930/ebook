package com.ebook.services;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;

import java.util.List;

public interface SensitiveWordsService {
    public List<SensitiveWords> getSensitiveWords(SensitiveWordsQuery sensitiveWordsQuery);
    public void save(SensitiveWords sensitiveWords);
    public SensitiveWords getById(String id);
    public void update(SensitiveWords sensitiveWords);

    public void delete(SensitiveWordsQuery query);
}
