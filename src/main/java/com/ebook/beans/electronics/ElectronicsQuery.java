package com.ebook.beans.electronics;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ElectronicsQuery extends BaseQuery {

    private String electronicsName; //电子名称
    private Integer electronicsType; //电子类型
    private Integer hasInvoice; //是否有发票

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date buyDate; //购买日期
}
