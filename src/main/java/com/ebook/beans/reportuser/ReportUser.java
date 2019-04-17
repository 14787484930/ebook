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

    private String id;

    @NotNull(message = "举报人不能为空")
    private User userId;

    @NotNull(message = "举报辅导不能为空")
    private String tutoringId;

    @NotNull(message = "描述不能为空")
    private String des;

    private Date createTime;

    private User createUser;

    private Integer dataStatus;
}
