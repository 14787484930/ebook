package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportTypeInfo;
import com.ebook.beans.reportuser.ReportTypeInfoQuery;
import com.ebook.daos.ReportTypeInfoDao;
import com.ebook.services.ReportTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTypeInfoServiceImpl implements ReportTypeInfoService {

    @Autowired
    ReportTypeInfoDao reportTypeInfoDao;

    @Override
    public List<ReportTypeInfo> getReportTypeInfo() {
        return null;
    }

    @Override
    public void save(ReportTypeInfo other) {

    }

    @Override
    public ReportTypeInfo getById(ReportTypeInfoQuery query) {
        return null;
    }

    @Override
    public void update(ReportTypeInfo other) {

    }

    @Override
    public void delete(ReportTypeInfoQuery query) {

    }
}
