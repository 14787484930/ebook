package com.ebook.beans.good;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

@Data
public class GoodQuery extends BaseQuery {

    private String goodName; //商品名称

    private String supperId; //小卖铺标记（对应用户id）

}
