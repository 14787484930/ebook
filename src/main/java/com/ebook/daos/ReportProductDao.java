package com.ebook.daos;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;

import java.util.List;

public interface ReportProductDao {
    public List<ReportProduct> getReportProduct();
    public void save(ReportProduct other);
    public ReportProduct getById(ReportProductQuery query);
    public void update(ReportProduct other);
    public void delete(ReportProductQuery query);
}
