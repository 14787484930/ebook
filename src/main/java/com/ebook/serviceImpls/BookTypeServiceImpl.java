package com.ebook.serviceImpls;

import com.ebook.beans.booktype.BookType;
import com.ebook.beans.booktype.BookTypeQuery;
import com.ebook.daos.BookTypeDao;
import com.ebook.services.BookTypeService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author zxl
 * @date 2018/12/20 11:49
 * @describe
 */
@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeDao bookTypeDao;

    /**
     * zxl
     * @param query
     * @return
     * 2018-12-20
     * 查询图书类型列表
     */
    @Override
    public List<BookType> getBookTypes(BookTypeQuery query) {

        return bookTypeDao.getBookTypes(query);
    }

    /**
     * zxl
     * @param bookType
     * 2018-12-20
     * 保存图书类型列表
     */
    @Override
    public void save(BookType bookType) {

        bookType.setId(GeneratingId.getId());
        bookTypeDao.save(bookType);
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018-12-20
     * 查询图书类型详情
     */
    @Override
    public BookType getById(String id) {

        return bookTypeDao.getById(id);
    }

    /**
     * zxl
     * @param bookType
     * 2018-12-20
     * 编辑图书类型
     */
    @Override
    public void update(BookType bookType) {

        bookTypeDao.update(bookType);
    }

    /**
     * zxl
     * @param query
     * 2018-12-20
     * 删除图书类型
     */
    @Override
    public void delete(BookTypeQuery query) {

        bookTypeDao.delete(query);
    }
}
