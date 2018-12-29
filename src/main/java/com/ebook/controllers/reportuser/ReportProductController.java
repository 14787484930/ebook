package com.ebook.controllers.reportuser;

import com.ebook.beans.book.Book;
import com.ebook.beans.book.BookQuery;
import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;
import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportProductQuery;
import com.ebook.beans.user.User;
import com.ebook.services.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author zxl
 * @date 2018/12/26 16:36
 * @describe
 * 用户举报产品（图书，电子，其他）
 */

@Controller
@RequestMapping("/reportproduct")
public class ReportProductController {

    @Autowired
    ReportProductService reportProductService;

    @Autowired
    BookService bookService;

    @Autowired
    ElectronicsService electronicsService;

    @Autowired
    OtherService otherService;

    /**
     * zxl
     * @param reportProductQuery
     * @return
     * 2018/12/26
     * 获取举报列表
     */
    @RequestMapping("/reportproducts")
    @ResponseBody
    public Object reportProducts(ReportProductQuery reportProductQuery){

        PageHelper.startPage(reportProductQuery.getPageNumber(),reportProductQuery.getPageSize());

        List<ReportProduct> list = reportProductService.getReportProducts(reportProductQuery);

        PageInfo<ReportProduct> pageInfo = new PageInfo<ReportProduct>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo);
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018/12/26
     * 查看举报详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",reportProductService.getById(id));
    }


    /**
     * zxl
     * @param reportProductQuery
     * @param session
     * @return
     * 2018/12/26
     * 处理举报
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(ReportProductQuery reportProductQuery, HttpSession session){

        Integer productType =  reportProductQuery.getProductType();
        String productId = reportProductQuery.getProductId();
        if(productType == null && StringUtils.isEmpty(productId)){
            return ResultInfo.fail().add("errors","传参有误");
        }

        switch (productType){
            case 1:
                BookQuery bookQuery = new BookQuery();
                bookQuery.setId(productId);
                bookService.delete(bookQuery);
                break;
            case 2:
                ElectronicsQuery electronicsQuery = new ElectronicsQuery();
                electronicsQuery.setId(productId);
                electronicsService.delete(electronicsQuery);
                break;
            case 3:
                OtherQuery otherQuery = new OtherQuery();
                otherQuery.setId(productId);
                otherService.delete(otherQuery);
                break;
        }
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param reportProductQuery
     * @return
     * 2018/12/26
     * 删除举报
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(ReportProductQuery reportProductQuery){

        reportProductService.delete(reportProductQuery);
        return ResultInfo.success();
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(ReportProduct reportProduct, HttpSession session){

        reportProduct.setCreateTime(new Date());
        String weiXin = ((User)session.getAttribute("userinfo")).getWeiXin();
        if(weiXin != null && !weiXin.equals("")){
            reportProduct.setWeiXin(weiXin);
        }
        reportProductService.save(reportProduct);
        return ResultInfo.success();
    }


    /**
     * zxl
     * @param reportProductQuery
     * @return
     * 2018/12/26
     * 查看被举报的产品
     */
    @RequestMapping("/getProduct")
    @ResponseBody
    public Object getProduct(ReportProductQuery reportProductQuery){

        Integer productType =  reportProductQuery.getProductType();
        String productId = reportProductQuery.getProductId();
        if(productType == null && StringUtils.isEmpty(productId)){
            return ResultInfo.fail().add("errors","传参有误");
        }

        ResultInfo ref = ResultInfo.success();

        switch (productType){
            case 1:
                ref.add("info",bookService.getById(productId));
                break;
            case 2:
                ref.add("info",electronicsService.getById(productId));
                break;
            case 3:
                ref.add("info",otherService.getById(productId));
                break;
        }
        return ref;

    }

}
