package com.ebook.services;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;

import java.util.List;

public interface SensitiveWordsService {
    public List<SensitiveWords> getSensitiveWords();
    public void save(SensitiveWords sensitiveWords);
    public SensitiveWords getById(SensitiveWordsQuery query);
    public void update(SensitiveWords other);

    public void delete(SensitiveWordsQuery query);
}
