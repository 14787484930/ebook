package com.ebook.controllers.tutoring;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;
import com.ebook.services.TutoringService;
import com.ebook.services.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import com.model.utills.validate.ValidateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author zxl
 * @date 2018/12/21
 * @describe
 * 辅导控制器
 */
@Controller
@RequestMapping("/tutoring")
public class TutoringController {

    @Autowired
    TutoringService tutoringService;

    /**
     *zxl
     * @param tutoringQuery
     * @param session
     * @return
     * 2018/12/21
     * 查询辅导列表
     */
    @RequestMapping("/tutorings")
    @ResponseBody
    public Object getUsers(TutoringQuery tutoringQuery, HttpSession session){

        tutoringQuery.intiQuery(session);

        PageHelper.startPage(tutoringQuery.getPageNumber(),tutoringQuery.pageSize);

        List<Tutoring> list = tutoringService.getTutorings(tutoringQuery);

        PageInfo<Tutoring> pageInfo = new PageInfo<Tutoring>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo).add("userinfo",tutoringQuery.getUser());
    }


    /**
     * zxl
     * @param id
     * @return
     * 2018/12/21
     * 查询辅导详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",tutoringService.getById(id));
    }

    /**
     * zxl
     * @param tutoring
     * @param result
     * @return
     * 2018/12/21
     * 编辑辅导信息
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid Tutoring tutoring,BindingResult result){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        tutoring.setUpdateTime(new Date());

        tutoringService.update(tutoring);

        return ResultInfo.success();
    }

    /**
     * zxl
     * @param tutoringQuery
     * @return
     * 2018/12/21
     * 删除用户信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(TutoringQuery tutoringQuery){

        tutoringService.delete(tutoringQuery);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param tutoring
     * @return
     *2018/12/21
     * 添加辅导信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid Tutoring tutoring, BindingResult result,HttpSession session){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        tutoring.setCreateTime(new Date());

        tutoring.setCreateUser((User)session.getAttribute("userInfo"));

        tutoringService.save(tutoring);

        return ResultInfo.success();
    }
}
