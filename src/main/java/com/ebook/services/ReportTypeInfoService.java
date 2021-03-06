package com.ebook.services;

import com.ebook.beans.reportuser.ReportTypeInfo;
import com.ebook.beans.reportuser.ReportTypeInfoQuery;

import java.util.List;

public interface ReportTypeInfoService {
    public List<ReportTypeInfo> getReportTypeInfo(ReportTypeInfoQuery query);
    public void save(ReportTypeInfo other);
    public ReportTypeInfo getById(String id);
    public void update(ReportTypeInfo other);
    public void delete(ReportTypeInfoQuery query);

}
