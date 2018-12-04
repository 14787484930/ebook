package com.ebook.beans.dictionary;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DataDictionary implements Serializable {
    @NotNull(message = "查询类型编号不能为null")
    private Integer id;

    @NotEmpty(message = "数据类型名称不能为空")
    private String dicType;

    @NotEmpty(message = "数据类型描述不能为空")
    private String dicName;

    @NotNull(message = "数据状态不能为null")
    private Integer dataStatus;
}
