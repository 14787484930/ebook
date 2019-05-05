package com.ebook.controllers.order;

import com.ebook.beans.good.Good;
import com.ebook.beans.good.GoodQuery;
import com.ebook.beans.order.Order;
import com.ebook.beans.order.OrderQuery;
import com.ebook.beans.user.User;
import com.ebook.services.GoodService;
import com.ebook.services.OrderService;
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
 * @date 2019/5/5 15:22
 * @describe
 */
@CrossOrigin
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderService orderService;


    /**
     * zxl
     * @param orderQuery
     * @param session
     * @return
     * 2019/05/05
     *查看指定小卖铺的列表
     */
    @CrossOrigin
    @RequestMapping("/orders")
    @ResponseBody
    public Object getGoods(OrderQuery orderQuery, HttpSession session){

        //权限初始化
        orderQuery.intiQuery(session);
        //分页参数
        PageHelper.startPage(orderQuery.getPageNumber(),orderQuery.getPageSize());

        List<Order> list = orderService.getOrders(orderQuery);

        PageInfo<Order> pageInfo = new PageInfo<Order>(list,10);

        return ResultInfo.success().add("pageInfo",pageInfo).add("userInfo",orderQuery.getUser());
    }

    /**
     * zxl
     * @param orderQuery
     * @return
     * 2019/05/05
     *查看其他产品详情
     */
    @CrossOrigin
    @RequestMapping("/getDetailed")
    @ResponseBody
    @SysLog(moduleName = "查看详细信息")
    public Object getByIdOther(OrderQuery orderQuery){

        return ResultInfo.success().add("listinfo",orderService.getDetailed(orderQuery));
    }

    /**
     * zxl
     * @param order
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
    public Object save(@Valid Order order, BindingResult result,
                       HttpSession session) throws Exception {

        /*服务器端校验*/
        if(result.hasErrors()){
            //校验失败
            return ResultInfo.fail().add("errors", ValidateDate.checkDate(result));
        }

        order.setCreateUser((User)session.getAttribute("userInfo"));
        order.setCreateTime(new Date());

        //保存商品
        orderService.saveOrder(order);

        return ResultInfo.success();
    }
}
