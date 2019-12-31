package com.ebook.beans.tutoring;

import com.ebook.beans.base.BaseQuery;
import com.ebook.beans.reportuser.ReportUser;
import lombok.Data;


@Data
public class TutoringQuery extends BaseQuery {

    private String name; //辅导名称
    private Integer type; //辅导类型
    private String checkCode; //接单校验
    private Integer hasInvoice; //是否有发票
    private String orderUser; //接单人
    private double score;

  /*  @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime; //结束时间

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime; //开始时间*/

    //举报
    private ReportUser reportUser;
    private Integer isReport; //是否举报{0：不举报，1：举报}
    private Integer isScore; //是否评价了{0：没有评分过，1：已经评分过了}
    private String des;

}
