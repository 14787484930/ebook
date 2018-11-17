package com.ebook.daos;

import com.ebook.beans.book.Book;

import java.util.List;

public interface BookDao {

    public List<Book> getBooks();
    public void save(Book book);
}
