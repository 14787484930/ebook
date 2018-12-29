package com.ebook.daos;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;

import java.util.List;

public interface ReportUserDao {
    public List<ReportUser> getReportUser(ReportUserQuery query);
    public void save(ReportUser reportUser);
    public ReportUser getById(String id);
    public void update(ReportUserQuery query);

    public void delete(ReportUserQuery query);
}
