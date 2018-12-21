package com.ebook.serviceImpls;

import com.ebook.beans.electronicstype.ElectronicsType;
import com.ebook.beans.electronicstype.ElectronicsTypeQuery;
import com.ebook.daos.ElectronicsTypeDao;
import com.ebook.services.ElectronicsTypeService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gpj
 * @date 2018/12/21 10:50
 * @describe
 */
@Service
public class ElectronicsTypeServiceImpl implements ElectronicsTypeService {

    @Autowired
    ElectronicsTypeDao electronicsTypeDao;

    /**
     * gpj
     * @param query
     * @return
     * 查询电子类型列表
     */
    @Override
    public List<ElectronicsType> getElectronicsType(ElectronicsTypeQuery query) {
        return electronicsTypeDao.getElectronicsType(query);
    }

    /**
     * gpj
     * @param electronicsType
     * 保存电子类型列表
     */
    @Override
    public void save(ElectronicsType electronicsType) {
        //注入主键
        electronicsType.setId(GeneratingId.getId());
        electronicsTypeDao.save(electronicsType);
    }

    /**
     * gpj
     * @param id
     * @return
     * 查询电子产品类型详情
     */
    @Override
    public ElectronicsType getById(String id) {
        return electronicsTypeDao.getById(id);
    }

    /**
     * gpj
     * @param electronicsType
     * 编辑电子产品类型
     */
    @Override
    public void update(ElectronicsType electronicsType) {
        electronicsTypeDao.update(electronicsType);
    }

    /**
     * gpj
     * @param query
     * 删除电子类型
     */
    @Override
    public void delete(ElectronicsTypeQuery query) {
        electronicsTypeDao.delete(query);
    }
}
