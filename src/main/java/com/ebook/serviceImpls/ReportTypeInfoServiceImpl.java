package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportTypeInfo;
import com.ebook.beans.reportuser.ReportTypeInfoQuery;
import com.ebook.daos.ReportTypeInfoDao;
import com.ebook.services.ReportTypeInfoService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTypeInfoServiceImpl implements ReportTypeInfoService {

    @Autowired
    ReportTypeInfoDao reportTypeInfoDao;

    /**
     * gpj
     * @param query
     * @return
     * 2018-12-26
     * 查询举报类型列表
     */
    @Override
    public List<ReportTypeInfo> getReportTypeInfo(ReportTypeInfoQuery query) {
        return reportTypeInfoDao.getReportTypeInfo(query);
    }

    /**
     * gpj
     * @param reportTypeInfo
     * 2018-12-26
     * 保存举报类型列表
     */
    @Override
    public void save(ReportTypeInfo reportTypeInfo) {
        reportTypeInfo.setId(GeneratingId.getId());
        reportTypeInfoDao.save(reportTypeInfo);
    }

    /**
     * gpj
     * @param id
     * @return
     * 2018-12-26
     * 查询举报类型详情
     */
    @Override
    public ReportTypeInfo getById(String id) {
        return reportTypeInfoDao.getById(id);
    }

    /**
     * gpj
     * @param reportTypeInfo
     * 2018-12-26
     * 编辑举报类型
     */
    @Override
    public void update(ReportTypeInfo reportTypeInfo) {
        reportTypeInfoDao.update(reportTypeInfo);
    }

    /**
     * gpj
     * @param query
     * 2018-12-26
     * 删除举报类型
     */
    @Override
    public void delete(ReportTypeInfoQuery query) {
        reportTypeInfoDao.delete(query);
    }
}
