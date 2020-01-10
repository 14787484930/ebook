package com.ebook.beans.user;

import lombok.Data;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author zxl
 * @date 2018/12/20 17:51
 * @describe
 */
@Data
public class UserQuery {

    private String id; //用户编号
    private String studNo;     //学号
    private String weiXin; //用户微信
    private String nickname; //微信昵称你
    public Date startTime; //开始日期
    public Date endTime; //结束日期
    public String openId; //用户唯一标识
    public User user; //用户信息

    /*分页相关*/
    public Integer pageNumber; //当前页码
    public Integer pageSize; //页面容量

    /*小卖铺楼栋*/
    private Integer buildingNumber; //宿舍楼编号---针对小卖铺
    private String superMarketName; //超市名称
    private Integer buildingNum; //宿舍楼编号---针对普通用户

    /**
     * zxl
     * @param session
     * 获取当前用户的信息，方便返回前端
     */
    public void intiQuery(HttpSession session){

        this.user = (User)session.getAttribute("userInfo");
    }

}
