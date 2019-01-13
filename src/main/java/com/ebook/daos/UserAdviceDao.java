package com.ebook.daos;

import com.ebook.beans.about.UserAdvice;
import com.ebook.beans.about.UserAdviceQuery;

import java.util.List;

public interface UserAdviceDao {

    public List<UserAdvice> getUserAdvice(UserAdviceQuery query);
    public void save(UserAdvice userAdvice);
    public UserAdvice getById(String id);
    public void update(String id);
    public void delete(UserAdviceQuery query);
}
