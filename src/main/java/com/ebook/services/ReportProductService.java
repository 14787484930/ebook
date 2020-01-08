package com.ebook.services;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;

import java.util.List;

public interface ReportProductService {
    public List<ReportProduct> getReportProducts(ReportProductQuery reportProductQuery);
    public void save(ReportProduct other);
    public ReportProduct getById(String id);
    public void update(ReportProduct other);
    public int delete(ReportProductQuery query);
}
