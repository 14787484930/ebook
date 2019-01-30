package com.ebook.daos;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;

import java.util.List;

public interface SensitiveWordsDao {
    public List<SensitiveWords> getSensitiveWords(SensitiveWordsQuery sensitiveWordsQuery);
    public void save(SensitiveWords sensitiveWords);
    public SensitiveWords getById(String id);
    public void update(SensitiveWords other);

    public void delete(SensitiveWordsQuery query);
}
