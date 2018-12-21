package com.ebook.beans.other;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

import java.util.Date;

@Data
public class OtherQuery extends BaseQuery {

    private String name; //产品名称
    private Date buyTime; //购买时间
    private Integer hasInvoice; //是否有发票不能为空(0-没有 1-有)
}
