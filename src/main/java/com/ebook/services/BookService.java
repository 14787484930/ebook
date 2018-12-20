package com.ebook.services;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;

import java.util.List;

public interface BookService {

    public List<Book> getBooks(BookQuery query);
    public void save(Book book);
    public Book getById(String id);
    public void update(Book book);
    public void delete(BookQuery query);
}
