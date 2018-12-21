package com.ebook.controllers.user;

import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;

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
 * @date 2018/12/21 9:55
 * @describe
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *zxl
     * @param userQuery
     * @param session
     * @return
     * 2018/12/21
     * 查询用户列表
     */
    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers(UserQuery userQuery, HttpSession session){

        userQuery.intiQuery(session);

        PageHelper.startPage(userQuery.getPageNumber(),userQuery.pageSize);

        List<User> list = userService.getUsers(userQuery);

        PageInfo<User> pageInfo = new PageInfo<User>(list,10);

        return ResultInfo.success().add("pageinfo",pageInfo).add("userinfo",userQuery.getUser());
    }


    /**
     * zxl
     * @param id
     * @return
     * 2018/12/21
     * 查询用户详情
     */
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",userService.getById(id));
    }

    /**
     * zxl
     * @param user
     * @param result
     * @return
     * 2018/12/21
     * 编辑用户信息
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@Valid User user,BindingResult result){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        user.setUpdateTime(new Date());
        userService.update(user);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param userQuery
     * @return
     * 2018/12/21
     * 删除用户信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(UserQuery userQuery){

        userService.delete(userQuery);
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param user
     * @return
     *2018/12/21
     * 添加用户信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid User user,BindingResult result){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        user.setCreateTime(new Date());

        userService.save(user);

        return ResultInfo.success();
    }

}
