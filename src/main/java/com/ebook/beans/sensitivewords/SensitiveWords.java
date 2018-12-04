package com.ebook.beans.sensitivewords;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class SensitiveWords implements Serializable {
    @NotEmpty(message = "敏感词编号不能为空")
    private String id;

    @NotEmpty(message = "敏感词不能为空")
    private String word;
}
