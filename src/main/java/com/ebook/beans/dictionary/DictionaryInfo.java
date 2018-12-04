package com.ebook.beans.dictionary;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DictionaryInfo implements Serializable {
    @NotNull(message = "数据值编号不能null")
    private Integer id;

    @NotNull(message = "数据类型编号不能为null")
    private Integer dateDicId;

    @NotEmpty(message = "数据值不能为空")
    private String dicValue;

    @NotNull(message = "数据状态不能为null")
    private Integer dataStatus;
}
