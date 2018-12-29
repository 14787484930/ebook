package com.ebook.controllers.reportuser;

import com.ebook.beans.reportuser.ReportProduct;
import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.reportuser.ReportUserQuery;
import com.ebook.services.ReportUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zxl
 * @date 2018/12/29 11:47
 * @describe
 */
@Controller
@RequestMapping("/reportuser")
public class ReportUserController {

    @Autowired
    ReportUserService reportUserService;

    /**
     * zxl
     * @return
     * 2018/12/29
     * 查看举报用户列表
     */
    @RequestMapping("/reportusers")
    @ResponseBody
    public Object reportusers(ReportUserQuery reportUserQuery){

        PageHelper.startPage(reportUserQuery.getPageNumber(),reportUserQuery.getPageSize());

        List<ReportUser> list = reportUserService.getReportUser(reportUserQuery);

        PageInfo<ReportUser> pageInfo = new PageInfo<ReportUser>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo);
    }

    /**
     * zxl
     * @return
     * 2018/12/29
     * 查看举报
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",reportUserService.getById(id));
    }

    /**
     * zxl
     * @param reportUserQuery
     * @return
     * 2018/12/29
     * 删除举报
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(ReportUserQuery reportUserQuery){

        reportUserService.delete(reportUserQuery);
        return ResultInfo.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(ReportUserQuery reportUserQuery){

        reportUserService.update(reportUserQuery);

        return ResultInfo.success();
    }






}
