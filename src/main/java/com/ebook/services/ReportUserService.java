package com.ebook.services;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;

import java.util.List;

public interface ReportUserService {
    public List<ReportUser> getReportUser(ReportUserQuery query );
    public void save(ReportUser other);
    public ReportUser getById(String id);
    public void update(ReportUserQuery query);

    public void delete(ReportUserQuery query);
}
