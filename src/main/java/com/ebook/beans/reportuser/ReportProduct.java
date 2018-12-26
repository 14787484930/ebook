package com.ebook.beans.reportuser;

import com.ebook.beans.base.BaseBean;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class ReportProduct extends BaseBean {

    @NotEmpty(message = "产品编号不能为空")
    private String productId; //产品编号

    @NotEmpty(message = "产品名称不能为空")
    private String productName; //产品名称

    @NotEmpty(message = "产品类型不能为空")
    private Integer productType; //产品类型 {1：图书，2：电子，3：其他}

    @NotEmpty(message = "举报类型不能为空")
    private String reportType; //举报类型

    private String weiXin; //举报者

    private String des; //描述


}
