package com.ebook.beans.about;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

import java.util.Date;

@Data
public class UserAdviceQuery extends BaseQuery {
    private String id;
    //根据日期查询
    private Date createTime;
    //数据状态(信息是否已经查看)
    private Integer dataStatus; //1 100 0

    /*分页相关*/
    public Integer pageNumber; //当前页码
    public Integer pageSize; //页面容量
}
