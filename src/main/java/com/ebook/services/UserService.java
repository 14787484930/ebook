package com.ebook.services;

import com.ebook.beans.book.Book;
import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;

import java.util.List;

public interface UserService {

    public List<User> getUsers(UserQuery query);
    public void save(User user);
    public User getById(String id);
    public void update(User user);
    public void delete(UserQuery query);
}
