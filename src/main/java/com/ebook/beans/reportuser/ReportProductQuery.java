package com.ebook.beans.reportuser;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

@Data
public class ReportProductQuery extends BaseQuery {

    private String productName; //产品名称
    private Integer reportType; //举报类型
    private Integer productType; //产品类型 {1：图书，2：电子，3：其他}
    private String productId; //产品编号
}
