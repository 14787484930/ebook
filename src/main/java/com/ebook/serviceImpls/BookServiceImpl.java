package com.ebook.serviceImpls;

import com.ebook.beans.book.Book;
import com.ebook.daos.BookDao;
import com.ebook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBooks() {

        List<Book> list = bookDao.getBooks();
        return list;
    }

    @Override
    public void save(Book book) {

        //注入主键
        //book.setId(GeneratingId.getId());
        bookDao.save(book);
    }
}
