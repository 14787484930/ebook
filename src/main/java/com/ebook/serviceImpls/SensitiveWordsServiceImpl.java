package com.ebook.serviceImpls;

import com.ebook.beans.sensitivewords.SensitiveWords;
import com.ebook.beans.sensitivewords.SensitiveWordsQuery;
import com.ebook.daos.SensitiveWordsDao;
import com.ebook.services.SensitiveWordsService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
    @Autowired
    SensitiveWordsDao sensitiveWordsDao;

    @Override
    public List<SensitiveWords> getSensitiveWords(SensitiveWordsQuery sensitiveWordsQuery) {

        return sensitiveWordsDao.getSensitiveWords(sensitiveWordsQuery);
    }

    @Override
    public void save(SensitiveWords sensitiveWords) {

        sensitiveWords.setId(GeneratingId.getId());
        sensitiveWordsDao.save(sensitiveWords);
    }

    @Override
    public SensitiveWords getById(String id) {

        return sensitiveWordsDao.getById(id);
    }

    @Override
    public void update(SensitiveWords sensitiveWords) {

        sensitiveWordsDao.update(sensitiveWords);
    }

    @Override
    public void delete(SensitiveWordsQuery query) {

        sensitiveWordsDao.delete(query);
    }
}
