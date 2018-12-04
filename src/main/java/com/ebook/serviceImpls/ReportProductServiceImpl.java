package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;
import com.ebook.daos.ReportProductDao;
import com.ebook.services.ReportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportProductServiceImpl implements ReportProductService {
    @Autowired
    ReportProductDao reportProductDao;

    @Override
    public List<ReportProduct> getReportProduct() {
        return null;
    }

    @Override
    public void save(ReportProduct other) {

    }

    @Override
    public ReportProduct getById(ReportProductQuery query) {
        return null;
    }

    @Override
    public void update(ReportProduct other) {

    }

    @Override
    public void delete(ReportProductQuery query) {

    }
}
