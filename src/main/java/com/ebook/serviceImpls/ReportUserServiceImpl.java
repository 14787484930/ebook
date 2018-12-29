package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;
import com.ebook.daos.ReportUserDao;
import com.ebook.services.ReportUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportUserServiceImpl implements ReportUserService {

    @Autowired
    ReportUserDao reportUserDao;

    @Override
    public List<ReportUser> getReportUser(ReportUserQuery query) {

        return reportUserDao.getReportUser(query);
    }

    @Override
    public void save(ReportUser other) {

    }

    @Override
    public ReportUser getById(String id) {

        return reportUserDao.getById(id);
    }

    @Override
    public void update(ReportUserQuery query) {

        reportUserDao.update(query);
    }

    @Override
    public void delete(ReportUserQuery query) {

        reportUserDao.delete(query);
    }
}
