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
    public void updateWarning(BookQuery query); //系统标记预警信息
    public void updateDel(BookQuery query); //管理员处理预警信息
    public void updateViews(BookQuery query); //浏览量的统计
}
