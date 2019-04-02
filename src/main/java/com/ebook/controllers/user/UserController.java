package com.ebook.controllers.user;

import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;

import com.ebook.services.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.crawler.Crawler;
import com.model.utills.messages.ResultInfo;
import com.model.utills.validate.ValidateDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    /**
     * zxl
     * @param
     * @return
     *2019/04/02
     * 获取身份认证密钥
     */
    @CrossOrigin
    @RequestMapping("/getKey")
    @ResponseBody
    public Object getKey(){

        String theKey;
        try {
            theKey = Crawler.LocationStr();
        }catch (Exception e){
            return ResultInfo.fail();
        }

        //theKey = null;

        if(theKey != null){
            return ResultInfo.success().add("location",theKey);
        }else{
            return ResultInfo.fail();
        }

    }


    /**
     * zxl
     * @param
     * @return
     *2019/04/02
     * 进行身份认证
     */
    @CrossOrigin
    @RequestMapping("/authentication")
    @ResponseBody
    public Object identityAuthentication(@Valid User user,BindingResult result,HttpSession session){

        //参数校验
        if(result.hasErrors()){
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        Map<String,String> map = new HashMap<String,String>();

        map.put("TextBox1",user.getStudNo());
        map.put("TextBox2",user.getPassword());
        map.put("TextBox3",user.getValidCode());
        map.put("url", user.getUrl());

        try {
            if(Crawler.WebLogin(map)) {

                //认证成功，将认证结果写入数据库
                /*User userInfo = (User)session.getAttribute("userInfo");
                userInfo.setCreateTime(new Date());
                userInfo.setEmail(user.getEmail());
                userInfo.setStudNo(user.getStudNo());
                userService.save(userInfo);*/

                //响应认证结果
                return ResultInfo.success();
            }else{
                ///用户名密码或验证码输入有误
                return ResultInfo.fail().add("errors","请输入正确的学号、密码和验证码！");
            }
        } catch (IOException e) {
            //系统异常
            return ResultInfo.fail().add("errors","系统异常，请稍后重试！");
        }


    }
}
