package com.ebook.controllers.tutoring;

import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.beans.user.User;
import com.ebook.services.TutoringService;
import com.ebook.services.UserService;
import com.ebook.sys.log.SysLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.utills.messages.ResultInfo;
import com.model.utills.randomnum.RandomNum;
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

    @Autowired
    UserService userService;

    /**
     *zxl
     * @param tutoringQuery
     * @param session
     * @return
     * 2018/12/21
     * 接单人查看自己结果的订单
     */
    @CrossOrigin
    @RequestMapping("/tutorings")
    @ResponseBody
    public Object getUsers(TutoringQuery tutoringQuery, HttpSession session){


        //加入权限
        if(session.getAttribute("flag") != null) {
            tutoringQuery.setFlag(Integer.valueOf(session.getAttribute("flag")+""));
        }

        //判断用户查询的是已完成还是未完成(flag:0 我要购买，flag:1 我要发布)       isScore; 是否评价了{0：没有评分过，1：已经评分过了}
        if(tutoringQuery.getFlag() == 1 && tutoringQuery.getIsScore() == null){
            //当没有传isScore的值时设置默认值，查询未完成订单
            tutoringQuery.setIsScore(0);
        }

        tutoringQuery.intiQuery(session);

        PageHelper.startPage(tutoringQuery.getPageNumber(),tutoringQuery.pageSize);

        List<Tutoring> list = tutoringService.getTutorings(tutoringQuery);

        PageInfo<Tutoring> pageInfo = new PageInfo<Tutoring>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",tutoringQuery.getUser());
    }

    /**
     *zxl
     * @param tutoringQuery
     * @param session
     * @return
     * 2018/12/21
     * 查询辅导列表
     */
    @CrossOrigin
    @RequestMapping("/tutoringsByUser")
    @ResponseBody
    public Object getUsersByUser(TutoringQuery tutoringQuery, HttpSession session){

        tutoringQuery.intiQuery(session);

        PageHelper.startPage(tutoringQuery.getPageNumber(),tutoringQuery.pageSize);

        List<Tutoring> list = tutoringService.getTutoringsByUser(tutoringQuery);

        PageInfo<Tutoring> pageInfo = new PageInfo<Tutoring>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",tutoringQuery.getUser());
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
    @CrossOrigin
    @SysLog(moduleName = "查看详细信息")
    public Object getByIdTutoring(@PathVariable("id") String id){

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
    @CrossOrigin
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
    @CrossOrigin
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
    @CrossOrigin
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

    /**
     * zxl
     * @return
     * 2018/12/27
     * 用户接单
     */
    @CrossOrigin
    @RequestMapping("/getOrder")
    @ResponseBody
    public Object getOrder(TutoringQuery tutoringQuery,HttpSession session){

        //判断当前用户是否认证过
        /*if(StringUtils.isEmpty(((User)session.getAttribute("userinfo")).getId())){

            return ResultInfo.fail().add("msg","进行学生身份认证后才能接单！").add("code",101);
        }*/
        if(session.getAttribute("userInfo") == null){

            return ResultInfo.fail().add("msg","进行学生身份认证后才能接单！").add("code",101);
        }

        //需要得到校验码并判断是否正确
        String checkCode1 = tutoringQuery.getCheckCode(); //用户提交过来的校验码
        String checkCode2 = tutoringService.getById(tutoringQuery.getId()).getCheckCode(); //数据库中获取的校验码

        if(!checkCode1.equals(checkCode2)){
            return ResultInfo.fail().add("msg","您输入的接单码有误！");
        }

        //需要得到接单用户id（从session中取）
        tutoringQuery.setOrderUser(((User)session.getAttribute("userInfo")).getId());

        //调用service接单操作
        tutoringService.updateOrderUser(tutoringQuery);

        return ResultInfo.success().add("msg","接单成功！");
    }

    /**
     * zxl
     * @return
     * 2018/12/27
     * 撤销接单
     */
    @CrossOrigin
    @RequestMapping("/delOrder")
    @ResponseBody
    public Object deleteOrderUser(TutoringQuery tutoringQuery){

        //生成校验码覆盖原来的
        tutoringQuery.setCheckCode(RandomNum.getRandomNumer());

        //接单人覆盖为null
        tutoringQuery.setOrderUser(null);

        //调用service更新接单
        tutoringService.deleteOrderUser(tutoringQuery);

        return ResultInfo.success();
    }

    /**
     * zxl
     * @return
     * 2018/12/27
     * 评价用户
     */
    @CrossOrigin
    @RequestMapping("/updateScore")
    @ResponseBody
    public Object updateUserScore(TutoringQuery tutoringQuery,HttpSession session){

        //获取接单人信息
        User user = userService.getById(tutoringQuery.getOrderUser());

        //获取辅导信息
        Tutoring tutoring = tutoringService.getById(tutoringQuery.getId());

        //计算新的平均分
        int scoreNumber = user.getScoreNumber(); //获取评分次数
        double score = user.getScore();

        if(user.getScoreNumber() != 0){
            score = (score * scoreNumber + tutoringQuery.getScore())/(scoreNumber+=1);
        }else{
            score = tutoringQuery.getScore();
            scoreNumber = scoreNumber += 1;
        }

        //封装给用户打分的参数
        user.setScore(score);
        user.setScoreNumber(scoreNumber);

        ReportUser reportUser = new ReportUser();
        reportUser.setTutoringId(tutoringQuery.getId());
        //判断是否进行了举报
        if(tutoringQuery.getIsReport() == 1){
            reportUser.setCreateTime(new Date());
            reportUser.setCreateUser((User)session.getAttribute("userInfo"));
            reportUser.setId(GeneratingId.getId());
        }

        tutoringService.updateUserScore(user,reportUser);
        return ResultInfo.success();
    }
}
