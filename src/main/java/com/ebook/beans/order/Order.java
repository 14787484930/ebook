package com.ebook.beans.order;

import com.ebook.beans.base.BaseBean;
import com.ebook.beans.good.Good;
import com.model.utills.constants.Constant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class Order extends BaseBean {

    @NotNull(message = "手机不能为空")
    @Pattern(regexp = Constant.PHONE_NUMBER, message = "输入的手机号不合法")
    private String phone; //订单联系人电话

    @NotNull(message = "宿舍号不能为空！")
    private String address; //订单地址

    @NotNull(message = "商品数量不能为空！")
    @Min(value = 1, message = "最小值为1")
    private Integer totalNum; //订单商品总数量

    @NotNull(message = "总金额不能为空！")
    @Min(value = 0, message = "最小值为0")
    private Double totalCost; //订单总金额

    private List<Good> goods; //订单详细信息
    private String goodsStr; //订单商品
}
