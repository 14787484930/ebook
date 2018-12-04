package com.ebook.services;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;

import java.util.List;

public interface ReportUserService {
    public List<ReportUser> getReportUser();
    public void save(ReportUser other);
    public ReportUser getById(ReportUserQuery query);
    public void update(ReportUser other);

    public void delete(ReportUserQuery query);
}
