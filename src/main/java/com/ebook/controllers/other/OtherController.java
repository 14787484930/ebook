package com.ebook.controllers.other;

import com.ebook.beans.book.Book;
import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.beans.user.User;
import com.ebook.services.OtherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import com.model.utills.upload.PicUpload;
import com.model.utills.validate.ValidateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author zxl
 * @date 2018/12/21 14:56
 * @describe
 */
@Controller
@RequestMapping("/other")
public class OtherController {

    @Autowired
    public OtherService otherService;

    /**
     * zxl
     * @param otherQuery
     * @param session
     * @return
     * 2018/12/21
     *查看其他产品列表
     */
    @RequestMapping("/others")
    @ResponseBody
    public Object getOthers(OtherQuery otherQuery, HttpSession session){

        //分页参数
        PageHelper.startPage(otherQuery.getPageNumber(),otherQuery.getPageSize());

        List<Other> list = otherService.getOthers();

        PageInfo<Other> pageInfo = new PageInfo<Other>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo).add("userinfo",otherQuery.getUser());
    }

    /**
     * zxl
     * @param id
     * @return
     * 2018/12/21
     *查看其他产品详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",otherService.getById(id));
    }

    /**
     * zxl
     * @param other
     * @param files
     * @param result
     * @param session
     * @return
     * @throws Exception
     * 2018/12/21
     *编辑其他产品
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestParam(value="other",required=true) @Valid Other other,@RequestParam(value="files",required=false) MultipartFile[] files, BindingResult result,HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        String picStr = otherService.getById(other.getId()).getOtherPic();

        other.setOtherPic(PicUpload.uploadPic(files,session,"other"));

        otherService.update(other);

        PicUpload.delPic(picStr,session);

        return ResultInfo.success();
    }

    /**
     * zxl
     * @param otherQuery
     * @return
     * 2018/12/21
     *删除其他产品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(OtherQuery otherQuery){

        otherService.delete(otherQuery);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param other
     * @param files
     * @param result
     * @param session
     * @return
     * @throws Exception
     * 2018/12/21
     * 保存其他产品
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestParam(value="other",required=true) @Valid Other other, @RequestParam(value="files",required=false) MultipartFile[] files, BindingResult result, HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //图片的处理
        other.setOtherPic(PicUpload.uploadPic(files,session,"other"));
        other.setCreateUser((User)session.getAttribute("userInfo"));
        other.setCreateTime(new Date());

        //保存其他产品
        otherService.save(other);

        return ResultInfo.success();
    }

}
