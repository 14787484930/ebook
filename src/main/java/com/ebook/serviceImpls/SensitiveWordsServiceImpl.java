package com.ebook.serviceImpls;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;
import com.ebook.daos.SensitiveWordsDao;
import com.ebook.services.SensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
    @Autowired
    SensitiveWordsDao sensitiveWordsDao;

    @Override
    public List<SensitiveWords> getSensitiveWords() {
        return null;
    }

    @Override
    public void save(SensitiveWords sensitiveWords) {

    }

    @Override
    public SensitiveWords getById(SensitiveWordsQuery query) {
        return null;
    }

    @Override
    public void update(SensitiveWords other) {

    }

    @Override
    public void delete(SensitiveWordsQuery query) {

    }
}
