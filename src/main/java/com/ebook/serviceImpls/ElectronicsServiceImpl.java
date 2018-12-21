package com.ebook.serviceImpls;

import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;
import com.ebook.daos.BookDao;
import com.ebook.daos.ElectronicsDao;
import com.ebook.services.ElectronicsService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectronicsServiceImpl implements ElectronicsService {

    @Autowired
    ElectronicsDao electronicsDao;

    /**
     * gpj
     * @return
     * 查询电子详细信息
     */
    @Override
    public List<Electronics> getElectronics(ElectronicsQuery query) {
        List<Electronics> list = electronicsDao.getElectronics(query);
        return list;
    }

    /**
     * gpj
     * @param electronics
     * 新增电子
     */
    @Override
    public void save(Electronics electronics) {
        //注入主键
        electronics.setId(GeneratingId.getId());
        //保存数据
        electronicsDao.save(electronics);
    }

    /**
     * gpj
     * @param id
     * @return
     * 通过id查询电子产品详情
     */
    @Override
    public Electronics getById(String id) {
        return electronicsDao.getById(id);
    }

    /**
     * gpj
     * @param electronics
     * 编辑电子产品
     */
    @Override
    public void update(Electronics electronics) {
        electronicsDao.update(electronics);
    }

    /**
     * gpj
     * @param query
     */
    @Override
    public void delete(ElectronicsQuery query) {
        electronicsDao.delete(query);
    }
}
