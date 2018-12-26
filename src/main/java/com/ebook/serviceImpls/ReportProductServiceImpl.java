package com.ebook.serviceImpls;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;
import com.ebook.daos.ReportProductDao;
import com.ebook.services.ReportProductService;
import com.model.utills.uuid.GeneratingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportProductServiceImpl implements ReportProductService {
    @Autowired
    ReportProductDao reportProductDao;

    @Override
    public List<ReportProduct> getReportProducts(ReportProductQuery reportProductQuery) {

        return reportProductDao.getReportProducts(reportProductQuery);
    }

    @Override
    public void save(ReportProduct reportProduct) {

        reportProduct.setId(GeneratingId.getId());
        reportProductDao.save(reportProduct);
    }

    @Override
    public ReportProduct getById(String id) {

        return reportProductDao.getById(id);
    }

    @Override
    public void update(ReportProduct other) {

    }

    @Override
    public void delete(ReportProductQuery query) {

    }
}
