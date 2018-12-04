package com.ebook.daos;

import com.ebook.beans.about.UserAdvice;
import com.ebook.beans.about.UserAdviceQuery;

import java.util.List;

public interface UserAdviceDao {

    public List<UserAdvice> getUserAdvice();
    public void save(UserAdvice userAdvice);
    public UserAdvice getById(UserAdviceQuery query);
    public void update(UserAdvice userAdvice);
    public void delete(UserAdviceQuery query);
}
