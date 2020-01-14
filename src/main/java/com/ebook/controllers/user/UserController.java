package com.ebook.controllers.user;

import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;

import com.ebook.services.UserService;
import com.ebook.sys.log.SysLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.crawler.Crawler;
import com.model.utills.messages.ResultInfo;
import com.model.utills.validate.ValidateDate;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;

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
    @CrossOrigin
    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers(UserQuery userQuery, HttpSession session){

        userQuery.intiQuery(session);

        PageHelper.startPage(userQuery.getPageNumber(),userQuery.pageSize);

        List<User> list = userService.getUsers(userQuery);

        PageInfo<User> pageInfo = new PageInfo<User>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",userQuery.getUser());
    }


    /**
     * zxl
     * @param id
     * @return
     * 2018/12/21
     * 查询用户详情
     */
    @CrossOrigin
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){

        return ResultInfo.success().add("info",userService.getById(id));
    }

    /**
     * zxl
     * @param
     * @return
     * 2019/12/18
     * 查询用户详情
     */
    @CrossOrigin
    @RequestMapping("/getUser")
    @ResponseBody
    public Object getUser(HttpSession session){

        User user = (User)session.getAttribute("userInfo");
        return ResultInfo.success().add("userInfo",user);
    }


    /**
     * zxl
     * @param user
     * @param
     * @return
     * 2018/12/21
     * 编辑用户信息
     */
    @CrossOrigin
    @RequestMapping("/update")
    @ResponseBody
    public Object update(User user, HttpSession session){

        user.setUpdateTime(new Date());
        userService.update(user);
        UserQuery query = new UserQuery();
        query.setWeiXin(((User)session.getAttribute("userInfo")).getWeiXin());
        session.setAttribute("userInfo",userService.getByWeiXin(query));
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param userQuery
     * @return
     * 2018/12/21
     * 删除用户信息
     */
    @CrossOrigin
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
    @CrossOrigin
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

        //判断所填写的学号是否已经认证过了
        UserQuery query = new UserQuery();
        query.setStudNo(user.getStudNo());
        List<User> users = userService.getUsers(query);
        if(users.size() > 0){
            return ResultInfo.fail().add("errors", "此用户已经认证过了，请勿重复认证！");
        }

        try {
            if(Crawler.WebLogin(map)) {

                //认证成功，将认证结果写入数据库
                User userInfo = (User)session.getAttribute("userInfo");
                userInfo.setCreateTime(new Date());
                userInfo.setEmail(user.getEmail());
                userInfo.setStudNo(user.getStudNo());
                userInfo.setWeiXin(user.getWeiXin());
                userInfo.setPhone(user.getPhone());
                userService.save(userInfo);
                session.setAttribute("userInfo",userInfo);

                //响应认证结果
                return ResultInfo.success();
            }else{
                ///用户名密码或验证码输入有误
                return ResultInfo.fail().add("userInfo",session.getAttribute("userInfo")).add("errors","请输入正确的学号、密码和验证码！");
            }
        } catch (IOException e) {
            //系统异常
            return ResultInfo.fail().add("userInfo",session.getAttribute("userInfo")).add("errors","系统异常，请稍后重试！");
        }


    }

    /**
     * zxl
     * @param
     * @return
     *2019/05/06
     * 查看本栋楼小卖铺列表
     */
    @CrossOrigin
    @RequestMapping("/getsuperMarkets")
    @ResponseBody
    public Object getsuperMarkets(UserQuery userQuery, HttpSession session){

        userQuery.intiQuery(session);

        PageHelper.startPage(userQuery.getPageNumber(),userQuery.pageSize);

        List<User> list = userService.getsuperMarkets(userQuery);

        PageInfo<User> pageInfo = new PageInfo<User>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",userQuery.getUser());
    }

    /**
     * zxl
     * @param
     * @return
     * 2019/05/06
     * 首次进入小卖铺选择楼栋
     */
    @CrossOrigin
    @RequestMapping("/updateBuildingNum")
    @ResponseBody
    @SysLog(moduleName = "选择楼栋信息")
    public Object updateBuildingNum(UserQuery userQuery, HttpSession session){


        User user = ((User)session.getAttribute("userInfo"));
        userQuery.setId(user.getId());
        userService.updateBuildingNum(userQuery);
        UserQuery query = new UserQuery();
        query.setWeiXin(user.getWeiXin());
        session.setAttribute("userInfo",userService.getByWeiXin(query));
        return ResultInfo.success();
    }

    /**
     * zxl
     * @param userQuery
     * @return
     * 2019/05/06
     * 首次进入小卖铺选择楼栋
     */
    @CrossOrigin
    @RequestMapping("/updatebuildingNumber")
    @ResponseBody
    @SysLog(moduleName = "开通小卖铺")
    public Object updatebuildingNumber(UserQuery userQuery, HttpSession session){

        userService.updatebuildingNumber(userQuery);
        UserQuery query = new UserQuery();
        query.setWeiXin(((User)session.getAttribute("userInfo")).getWeiXin());
        session.setAttribute("userInfo",userService.getByWeiXin(query));

        return ResultInfo.success();
    }


    @CrossOrigin
    @RequestMapping("/checkinfo")
    @ResponseBody
    //@SysLog(moduleName = "开通小卖铺")
    public Object returnCheckInfo(HttpSession session){

        return ResultInfo.fail().add("mes","此用户尚未未认证！").add("userInfo",session.getAttribute("userInfo"));
    }


    @CrossOrigin
    @RequestMapping("/pclogin")
    @ResponseBody
    public Object pcLogin(User user,HttpSession session){

        if(StringUtils.isBlank(user.getWeiXin()) || StringUtils.isBlank(user.getStudNo())) {
            return ResultInfo.fail().add("msg","账号或密码不能为空！");
        }

        UserQuery query = new UserQuery();
        query.setOpenId(user.getOpenId());
        User u = userService.getByOpenId(query);

        if (u != null && user.getStudNo().equals(u.getStudNo())) {
            session.setAttribute("userInfo",u);
            return ResultInfo.success().add("msg","登录成功！");
        }else{
            return ResultInfo.fail().add("msg","账号或密码错误！");
        }
    }
}
