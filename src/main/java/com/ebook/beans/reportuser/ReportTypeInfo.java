package com.ebook.beans.reportuser;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class ReportTypeInfo implements Serializable {
    @NotEmpty(message = "ReportTypeInfo编号不能为空")
    private String id;

    @NotEmpty(message = "name不能为空")
    private String name;

    private String des;
}
