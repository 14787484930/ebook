package com.ebook.serviceImpls;

import com.ebook.beans.about.UserAdvice;
import com.ebook.beans.about.UserAdviceQuery;
import com.ebook.daos.UserAdviceDao;
import com.ebook.services.UserAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdviceServiceImpl implements UserAdviceService {
    @Autowired
    UserAdviceDao useradviceDao;

    @Override
    public List<UserAdvice> getUserAdvice() {
        return null;
    }

    @Override
    public void save(UserAdvice userAdvice) {

    }

    @Override
    public UserAdvice getById(UserAdviceQuery query) {
        return null;
    }

    @Override
    public void update(UserAdvice userAdvice) {

    }

    @Override
    public void delete(UserAdviceQuery query) {

    }
}
