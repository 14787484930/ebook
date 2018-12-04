package com.ebook.beans.reportuser;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class ReportProduct implements Serializable {
    @NotEmpty(message = "举报编号不能为空")
    private String id;

    @NotEmpty(message = "产品编号不能为空")
    private String productId;

    @NotEmpty(message = "举报类型不能为空")
    private String reportType;

    private String describe;

    @NotNull(message = "创建日期不能为空")
    @Past(message = "日期不合法")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @NotNull(message = "数据状态不能为空")
    private Integer dataStatus;
}
