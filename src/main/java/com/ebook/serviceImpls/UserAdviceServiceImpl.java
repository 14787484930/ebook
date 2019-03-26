package com.ebook.serviceImpls;

import com.ebook.beans.about.UserAdvice;
import com.ebook.beans.about.UserAdviceQuery;
import com.ebook.daos.UserAdviceDao;
import com.ebook.services.UserAdviceService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdviceServiceImpl implements UserAdviceService {
    @Autowired
    UserAdviceDao useradviceDao;

    /**
     * gpj
     * @return
     * 查询用户建议详细信息列表
     */
    @Override
    public List<UserAdvice> getUserAdvice(UserAdviceQuery query) {
        List<UserAdvice> list = useradviceDao.getUserAdvice(query);
        return list;
    }

    @Override
    public void save(UserAdvice userAdvice) {

        userAdvice.setId(GeneratingId.getId());
        useradviceDao.save(userAdvice);
    }

    @Override
    public UserAdvice getById(String id) {
        UserAdvice userAdvice = useradviceDao.getById(id);
        return userAdvice;
    }

    @Override
    public void update(String id) {
        useradviceDao.update(id);
    }

    @Override
    public void delete(UserAdviceQuery query) {
        useradviceDao.delete(query);
    }
}
