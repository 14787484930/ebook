package com.ebook.services;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;

import java.util.List;

public interface ReportProductService {
    public List<ReportProduct> getReportProduct();
    public void save(ReportProduct other);
    public ReportProduct getById(ReportProductQuery query);
    public void update(ReportProduct other);
    public void delete(ReportProductQuery query);
}
