package com.ebook.serviceImpls;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.daos.TutoringDao;
import com.ebook.services.TutoringService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutoringServiceImpl implements TutoringService {
    @Autowired
    TutoringDao tutoringDao;

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
}
