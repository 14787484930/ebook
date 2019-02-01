package com.ebook.serviceImpls;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;
import com.ebook.daos.BookDao;
import com.ebook.services.BookService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    /**
     * zxl
     * @return
     * 查询图书详细信息
     */
    @Override
    public List<Book> getBooks(BookQuery query) {

        List<Book> list = bookDao.getBooks(query);
        return list;
    }

    /**
     * zxl
     * @param book
     * 新增图书
     */
    @Override
    public void save(Book book) {

        //注入主键
        book.setId(GeneratingId.getId());
        //保存数据
        bookDao.save(book);
    }

    /**
     * zxl
     * @param id
     * @return
     * 通过id查询图书详情
     */
    @Override
    public Book getById(String id) {

        return bookDao.getById(id);
    }

    /**
     * zxl
     * @param book
     * 编辑图书
     */
    @Override
    public void update(Book book) {

        bookDao.update(book);
    }

    /**
     * zxl
     * @param query
     */
    @Override
    public void delete(BookQuery query) {

        bookDao.delete(query);
    }

    /**
     * zxl
     * @param query
     * 系统标记预警信息
     */
    @Override
    public void updateWarning(BookQuery query) {

        bookDao.updateWarning(query);
    }

    /**
     * zxl
     * @param query
     * 管理员处理预警信息
     */
    @Override
    public void updateDel(BookQuery query) {

        bookDao.updateDel(query);
    }
}
