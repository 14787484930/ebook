package com.ebook.controllers.useradvice;

import com.ebook.beans.about.UserAdvice;
import com.ebook.beans.about.UserAdviceQuery;
import com.ebook.beans.user.User;
import com.ebook.services.UserAdviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import com.model.utills.uuid.GeneratingId;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gpj
 * @date 2018/12/27
 * @describe
 */
@Controller
@RequestMapping("/useradvice")
public class UserAdviceController {

    @Autowired
    UserAdviceService userAdviceService;

    /**
     * gpj
     * @param userAdviceQuery
     * @param session
     * @return
     * 2018/12/27
     * 查询用户建议列表
     */
    @CrossOrigin
    @RequestMapping("/useradvices")
    @ResponseBody
    public Object getUsersAdvices(UserAdviceQuery userAdviceQuery, HttpSession session){

        //在查询之前注入页码和页面容量
        PageHelper.startPage(userAdviceQuery.getPageNumber(),userAdviceQuery.getPageSize());

        //查询数据
        List<UserAdvice> list = userAdviceService.getUserAdvice(userAdviceQuery);

        //包装查询后的结果(10表示导航器连续显示多少条)
        PageInfo<UserAdvice> pageInfo = new PageInfo<UserAdvice>(list,10);

        //封装数据
        return ResultInfo.success().add("pageInfo",pageInfo);
    }


    /**
     * gpj
     * @param id
     * @return
     * 2018/12/21
     * 查询建议详情
     */
    @CrossOrigin
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") String id){
        UserAdvice userAdvice = userAdviceService.getById(id);
        return ResultInfo.success().add("info",userAdvice);
    }

    /**
     * gpj
     * @param id
     * @return
     * 2019/1/2
     * 标记建议信息已读
     */
    @CrossOrigin
    @RequestMapping("/updata/{id}")
    @ResponseBody
    public void updataById(@PathVariable("id") String id){
        //更新数据数据状态
        userAdviceService.update(id);
    }

    /**
     * gpj
     * @param userAdvice
     * @return result
     * 2019/1/2
     * 提交建议信息
     */
    @CrossOrigin
    @RequestMapping("/save")
    @ResponseBody
    public Object Save(@Valid UserAdvice userAdvice, BindingResult result,HttpSession session){

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        //从session中获取用户信息
        User user = (User)session.getAttribute("userInfo");

        userAdvice.setCreateTime(new Date());
        userAdvice.setCreateUser(user);
        //userAdvice.setDataStatus(1);
        userAdvice.setDes(userAdvice.getDes());
        //userAdvice.setId(GeneratingId.getId());

        userAdviceService.save(userAdvice);
        return ResultInfo.success();
    }

    /**
     * gpj
     * @param userAdviceQuery
     * @return
     * 删除图书信息
     */
    @CrossOrigin
    @RequestMapping(value="/delete/{id}")
    @ResponseBody
    public Object delete(UserAdviceQuery userAdviceQuery, @PathVariable("id") String id){

        userAdviceQuery.setId(id);
        userAdviceService.delete(userAdviceQuery);
        return ResultInfo.success();
    }
}
