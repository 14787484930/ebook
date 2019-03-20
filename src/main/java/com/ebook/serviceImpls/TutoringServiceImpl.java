package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.beans.user.User;
import com.ebook.daos.ReportUserDao;
import com.ebook.daos.TutoringDao;
import com.ebook.daos.UserDao;
import com.ebook.services.ReportUserService;
import com.ebook.services.TutoringService;
import com.ebook.services.UserService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutoringServiceImpl implements TutoringService {
    @Autowired
    TutoringDao tutoringDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ReportUserDao reportUserDao;

    /**
     * zxl
     * @param query
     * @return
     * 2018-12-21
     * 查看辅导列表
     */
    @Override
    public List<Tutoring> getTutorings(TutoringQuery query) {

        return tutoringDao.getTutorings(query);
    }

    /**
     * zxl
     * @param tutoring
     * 2018-12-21
     * 保存辅导
     */
    @Override
    public void save(Tutoring tutoring) {

        tutoring.setId(GeneratingId.getId());
        tutoringDao.save(tutoring);
    }

    /**
     * zxl
     * @param id
     * @return
     * 查看辅导详情
     */
    @Override
    public Tutoring getById(String id) {

        return tutoringDao.getById(id);
    }

    /**
     * zxl
     * @param tutoring
     * 2018-12-21
     * 编辑辅导详情
     */
    @Override
    public void update(Tutoring tutoring) {

        tutoringDao.update(tutoring);
    }

    /**
     * zxl
     * @param query
     * 2018-12-21
     * 删除辅导详情
     */
    @Override
    public void delete(TutoringQuery query) {
        tutoringDao.delete(query);
    }

    /**
     * zxl
     * @param query
     * 2018-12-21
     * 用户接单的操作
     */
    @Override
    public void updateOrderUser(TutoringQuery query) {

        tutoringDao.updateOrderUser(query);
    }

    /**
     * zxl
     * @param query
     * 2018-12-21
     * 拒绝接单
     */
    @Override
    public void deleteOrderUser(TutoringQuery query) {

        tutoringDao.deleteOrderUser(query);
    }

    /**
     * zxl
     * @param user, reportUser
     * 2018-12-21
     * 评分
     */
    @Override
    public void updateUserScore(User user, ReportUser reportUser) {

        //给用户进行评分
        userDao.updateScore(user);

        //判断是否进行举报，是的话进行举报操作
        if(reportUser != null){
            reportUserDao.save(reportUser);
        }

        //将辅导编辑为已经举报过的
        tutoringDao.updateTutoring();
    }

    /**
     * zxl
     * @param query
     * 系统标记预警信息
     */
    @Override
    public void updateWarning(TutoringQuery query) {

        tutoringDao.updateWarning(query);
    }

    /**
     * zxl
     * @param query
     * 管理员处理预警信息
     */
    @Override
    public void updateDel(TutoringQuery query) {

        tutoringDao.updateDel(query);
    }


    /**
     * zxl
     * @param query
     * 2019-03-20
     * 浏览量的统计
     */
    @Override
    public void updateViews(TutoringQuery query) {

        tutoringDao.updateViews(query);
    }


}
