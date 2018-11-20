package com.ebook.daos;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;

import java.util.List;

public interface BookDao {

    public List<Book> getBooks();
    public void save(Book book);
    public Book getById(BookQuery query);
    public void update(Book book);
    public void delete(BookQuery query);
}
