package com.ebook.services;

import com.ebook.beans.booktype.BookType;
import com.ebook.beans.booktype.BookTypeQuery;

import java.util.List;

public interface BookTypeService {

    public List<BookType> getBookTypes(BookTypeQuery query);
    public void save(BookType bookType);
    public BookType getById(String id);
    public void update(BookType bookType);
    public void delete(BookTypeQuery query);
}
