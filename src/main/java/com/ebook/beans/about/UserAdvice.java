package com.ebook.beans.about;

import com.ebook.beans.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserAdvice implements Serializable {
    //用户建议编号
    private String id;

    @NotEmpty(message = "建议内容不能为空")
    private String des;

    @NotNull(message = "创建人不能为空")
    private User createUser;

    @NotNull(message = "日期不能为空")
    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    //数据状态不能为空
    //@NotNull(message = "数据状态不能为空")
    private Integer dataStatus;
}
