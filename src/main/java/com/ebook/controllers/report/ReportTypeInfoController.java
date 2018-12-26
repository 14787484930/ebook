package com.ebook.controllers.report;

import com.ebook.beans.reportuser.ReportTypeInfo;
import com.ebook.beans.reportuser.ReportTypeInfoQuery;
import com.ebook.services.ReportTypeInfoService;
import com.model.utills.messages.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author gpj
 * @date 2018/12/26 16:37
 * @describe
 */

@Controller
@RequestMapping("/report")
public class ReportTypeInfoController {
    @Autowired
    ReportTypeInfoService reportTypeInfoService;

    /**
     * gpj
     * @param reportTypeInfoQuery
     * @param session
     * @return
     * 2018年12月26日
     * 查看举报类型列表
     */
    @RequestMapping("/reporttypeinfo")
    @ResponseBody
    public Object getReportTypes(ReportTypeInfoQuery reportTypeInfoQuery, HttpSession session){
        return ResultInfo.success().add("reporttypeinfo",reportTypeInfoService.getReportTypeInfo(reportTypeInfoQuery));
    }

    /**
     * gpj
     * @param id
     * @return
     * 2018年12月26日
     * 查看举报类型详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getReportTypeById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",reportTypeInfoService.getById(id));
    }
    /**
     * gpj
     * @param reportTypeInfo
     * @return
     * 2018/12/26
     * 编辑举报类型
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid ReportTypeInfo reportTypeInfo){

        reportTypeInfoService.update(reportTypeInfo);
        return ResultInfo.success();
    }

    /**
     * gpj
     * @param id
     * @return
     * 2018/12/26
     * 删除举报类型
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id){

        ReportTypeInfoQuery query = new ReportTypeInfoQuery();
        query.setId(id);
        reportTypeInfoService.delete(query);

        return ResultInfo.success();
    }

    /**
     * gpj
     * @param reportTypeInfo
     * @return
     * 2018/12/26
     * 保存举报类型
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid ReportTypeInfo reportTypeInfo){

        reportTypeInfoService.save(reportTypeInfo);
        return ResultInfo.success();
    }
}
