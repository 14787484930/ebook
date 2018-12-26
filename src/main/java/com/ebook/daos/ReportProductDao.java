package com.ebook.daos;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;

import java.util.List;

public interface ReportProductDao {
    public List<ReportProduct> getReportProducts(ReportProductQuery reportProductQuery);
    public void save(ReportProduct other);
    public ReportProduct getById(String id);
    public void update(ReportProduct other);
    public void delete(ReportProductQuery query);
}
