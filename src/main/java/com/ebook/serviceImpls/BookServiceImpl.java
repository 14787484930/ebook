package com.ebook.serviceImpls;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;
import com.ebook.daos.BookDao;
import com.ebook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    /**
     *
     * @return
     * 查询图书详细信息
     */
    @Override
    public List<Book> getBooks() {

        List<Book> list = bookDao.getBooks();
        return list;
    }

    /**
     *
     * @param book
     * 新增图书
     */
    @Override
    public void save(Book book) {

        //注入主键
        //book.setId(GeneratingId.getId());
        bookDao.save(book);
    }

    /**
     *
     * @param query
     * @return
     * 通过id查询图书详情
     */
    @Override
    public Book getById(BookQuery query) {

        return bookDao.getById(query);
    }

    /**
     *
     * @param book
     * 编辑图书
     */
    @Override
    public void update(Book book) {

        bookDao.update(book);
    }

    @Override
    public void delete(BookQuery query) {

        bookDao.delete(query);
    }
}
