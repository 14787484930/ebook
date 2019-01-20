package com.ebook.controllers.electronics;

import com.ebook.beans.electronics.Electronics;
import com.ebook.beans.electronics.ElectronicsQuery;
import com.ebook.beans.user.User;
import com.ebook.services.ElectronicsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.log.SysLog;
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

@Controller
@RequestMapping("/electronics")
public class ElectronicsController {
    @Autowired
    ElectronicsService electronicsService;

    /**
     * gpj
     *
     * @param electronicsQuery
     * @param session
     * @return
     */
    @CrossOrigin
    @RequestMapping("/electronics")
    @ResponseBody
    @SysLog(moduleName = "gpj查看所有信息")
    public Object getElectronics(ElectronicsQuery electronicsQuery, HttpSession session) {
        //权限初始化
        electronicsQuery.intiQuery(session);

        //查询之前注入页码和页面容量
        PageHelper.startPage(electronicsQuery.getPageNumber(), electronicsQuery.getPageSize());

        //查询数据
        List<Electronics> list = electronicsService.getElectronics(electronicsQuery);

        //包装查询后的结果(10表示导航器连续显示多少条)
        PageInfo<Electronics> pageInfo = new PageInfo<>(list, 10);

        //封装数据
        return ResultInfo.success().add("pageInfo", pageInfo).add("userInfo", electronicsQuery.getUser());
    }

    /**
     * gpj
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getById/{id}")
    @ResponseBody
    @SysLog(moduleName = "gpj查看信息")
    public Object getById(@PathVariable("id") String id) {
        return ResultInfo.success().add("Info", electronicsService.getById(id));
    }

    /**
     * gpj
     *
     * @param electronics
     * @param result
     * @return 编辑电子信息
     */
    @CrossOrigin
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestParam(value = "electronics", required = true) @Valid Electronics electronics,
                         @RequestParam(value = "files", required = false) MultipartFile[] files, BindingResult result,
                         HttpSession session) throws Exception {

        /*服务器端校验*/
        if (result.hasErrors()) {
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //图片的处理
        electronics.setElectronicsPic(PicUpload.uploadPic(files, session, "electronics"));
        electronics.setUpdateUser((User) session.getAttribute("userInfo"));
        electronics.setUpdateTime(new Date());

        String picstr = electronicsService.getById(electronics.getId()).getElectronicsPic();

        //校验成功,,进行保存操作
        electronicsService.update(electronics);
        //编辑成功，干掉原来的图片
        PicUpload.delPic(picstr, session);
        return ResultInfo.success();
    }

    /**
     * gpj
     *
     * @param electronicsQuery
     * @return 删除电子信息
     */
    @CrossOrigin
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(ElectronicsQuery electronicsQuery) {
        electronicsService.delete(electronicsQuery);
        return ResultInfo.success();
    }

    /**
     * gpj
     *
     * @param electronics
     * @param result
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(@RequestParam(value = "electronics", required = true) @Valid Electronics electronics,
                       @RequestParam(value = "files", required = false) MultipartFile[] files,
                       BindingResult result, HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            ResultInfo.fail().add("error", ValidateDate.checkDate(result));
        }

        //图片处理
        electronics.setElectronicsPic(PicUpload.uploadPic(files,session,"electronics"));
        electronics.setCreateUser((User)session.getAttribute("userInfo"));
        electronics.setCreateTime(new Date());

        //校验成功，保存
        electronicsService.save(electronics);
        return ResultInfo.success();
    }
}