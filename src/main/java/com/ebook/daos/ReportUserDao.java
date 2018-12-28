package com.ebook.daos;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;

import java.util.List;

public interface ReportUserDao {
    public List<ReportUser> getReportUser();
    public void save(ReportUser reportUser);
    public ReportUser getById(ReportUserQuery query);
    public void update(ReportUser other);

    public void delete(ReportUserQuery query);
}
