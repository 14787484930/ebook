package com.ebook.beans.reportuser;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
public class ReportUser implements Serializable {
    @NotEmpty(message = "举报编号不能为空")
    private String id;

    @NotNull(message = "用户编号不能为空")
    private User userId;

    @NotNull(message = "辅导编号不能为空")
    private Tutoring TutoringId;

    private String des;

    @NotNull(message = "创建日期不能为空")
    @Past(message = "日期不合法")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @NotNull(message = "数据状态不能空")
    private Integer dataStatus;
}
