package com.ebook.controllers.good;

import com.ebook.beans.good.Good;
import com.ebook.beans.good.GoodQuery;
import com.ebook.beans.other.Other;
import com.ebook.beans.other.OtherQuery;
import com.ebook.beans.user.User;
import com.ebook.services.GoodService;
import com.ebook.services.OtherService;
import com.ebook.sys.log.SysLog;
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

import javax.management.Query;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author zxl
 * @date 2019/5/5 11:46
 * @describe
 */
@CrossOrigin
@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    public GoodService goodService;

    /**
     * zxl
     * @param goodQuery
     * @param session
     * @return
     * 2019/05/05
     *查看指定小卖铺的列表
     */
    @CrossOrigin
    @RequestMapping("/goods")
    @ResponseBody
    public Object getGoods(GoodQuery goodQuery, HttpSession session){

        User user = new User();
        user.setId("ea0b33ba3b65429f976a6514ae9296e8");
        user.setNickname("绿箭");
        user.setWeiXin("ZXL690345407");
        user.setPhone("14787484930");
        user.setOpenId("123456");
        session.setAttribute("userInfo",user);

        //权限初始化
        goodQuery.intiQuery(session);
        //分页参数
        PageHelper.startPage(goodQuery.getPageNumber(),goodQuery.getPageSize());

        List<Good> list = goodService.getGoods(goodQuery);

        PageInfo<Good> pageInfo = new PageInfo<Good>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",goodQuery.getUser());
    }

    /**
     * zxl
     * @param id
     * @return
     * 2019/05/05
     *查看其他产品详情
     */
    @CrossOrigin
    @RequestMapping("/getById/{id}")
    @ResponseBody
    @SysLog(moduleName = "查看详细信息")
    public Object getByIdOther(@PathVariable("id") String id){

        return ResultInfo.success().add("info",goodService.getById(id));
    }

    /**
     * zxl
     * @param good
     * @param files
     * @param result
     * @param session
     * @return
     * @throws Exception
     * 2019/05/05
     *编辑其他产品
     */
    @CrossOrigin
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid Good good, BindingResult result,
                         @RequestParam(value="files",required=false) MultipartFile[] files,
                         HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        String picStr = goodService.getById(good.getId()).getGoodPic();

        good.setGoodPic(PicUpload.uploadPic(files,session,"good"));

        goodService.update(good);

        PicUpload.delPic(picStr,session);

        return ResultInfo.success();
    }

    /**
     * zxl
     * @param goodQuery
     * @return
     * 2019/05/05
     *删除商品
     */
    @CrossOrigin
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(GoodQuery goodQuery){

        goodService.delete(goodQuery);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param good
     * @param files
     * @param result
     * @param session
     * @return
     * @throws Exception
     * 2019/05/05
     * 保存其他产品
     */
    @CrossOrigin
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid Good good, BindingResult result,
                       @RequestParam(value="files",required=false) MultipartFile[] files,
                       HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //图片的处理
        good.setGoodPic(PicUpload.uploadPic(files,session,"good"));
        good.setCreateUser((User)session.getAttribute("userInfo"));
        good.setCreateTime(new Date());

        //保存商品
        goodService.save(good);

        return ResultInfo.success();
    }

}
