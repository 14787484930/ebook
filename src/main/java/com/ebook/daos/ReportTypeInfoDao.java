package com.ebook.daos;

import com.ebook.beans.reportuser.ReportTypeInfo;
import com.ebook.beans.reportuser.ReportTypeInfoQuery;

import java.util.List;

public interface ReportTypeInfoDao {
    public List<ReportTypeInfo> getReportTypeInfo();
    public void save(ReportTypeInfo other);
    public ReportTypeInfo getById(ReportTypeInfoQuery query);
    public void update(ReportTypeInfo other);
    public void delete(ReportTypeInfoQuery query);
}
