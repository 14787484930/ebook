package com.ebook.beans.base;

import com.ebook.beans.user.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zxl
 * @date 2018/12/20 9:43
 * @describe
 */
@Data
public class BaseQuery implements Serializable {

    public String id; //编号
    public String[] ids;
    public User user; //用户信息
    public String createUser; //创建者

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date startTime; //开始日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date endTime; //结束日期

    public Double startPrice; //起始价格
    public Double endPrice; //结束价格

    public Integer viewTimes; //浏览次数

    /*设置一个状态参数。用于判断进入我要购买，还是我要发布界面*/
    public Integer flag; //{0：我要购买，1：我要发布}

    /*分页相关*/
    public Integer pageNumber; //当前页码
    public Integer pageSize; //页面容量


    /**
     * zxl
     * @param session
     * 获取当前用户的信息，方便返回前端
     */
    public void intiQuery(HttpSession session){

        this.user = (User)session.getAttribute("userInfo");
        if(user != null){
            this.createUser = user.getId();
        }

        if(this.endTime != null){
            Calendar c = Calendar.getInstance();
            c.setTime(this.endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);
            this.endTime = c.getTime();
        }
    }
}
