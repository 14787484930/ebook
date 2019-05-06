package com.ebook.serviceImpls;

import com.ebook.beans.book.Book;
import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;
import com.ebook.daos.UserDao;
import com.ebook.services.UserService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxl
 * @date 2018/12/21 9:43
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    /**
     * zxl
     * @param query
     * @return
     * 2018-12-21
     * 查询用户列表
     */
    @Override
    public List<User> getUsers(UserQuery query) {

        List<User> list = userDao.getUsers(query);
        return list;
    }

    /**
     * zxl
     * @param user
     * 2018-12-21
     * 添加用户
     */
    @Override
    public void save(User user) {

        user.setId(GeneratingId.getId());
        userDao.save(user);
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018-12-21
     * 查询用户详情
     */
    @Override
    public User getById(String id) {

        return userDao.getById(id);
    }

    /**
     * zxl
     * @param user
     * 2018-12-21
     * 编辑用户信息
     */
    @Override
    public void update(User user) {

        userDao.update(user);
    }

    /**
     * zxl
     * @param query
     * 2018-12-21
     * 删除用户
     */
    @Override
    public void delete(UserQuery query) {

        userDao.delete(query);
    }

    @Override
    public List<User> getsuperMarkets(UserQuery query) {

        List<User> superMarkets = userDao.getsuperMarkets(query);
        return superMarkets;
    }
}
