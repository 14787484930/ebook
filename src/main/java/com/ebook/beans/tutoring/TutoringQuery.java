package com.ebook.beans.tutoring;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;


@Data
public class TutoringQuery extends BaseQuery {

    private String name; //辅导名称
    private Integer type; //辅导类型

}
