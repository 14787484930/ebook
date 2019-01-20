package com.ebook.beans.electronics;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

@Data
public class ElectronicsQuery extends BaseQuery {
    private String electronicsName; //电子名称
    private Integer electronicsType; //电子类型
}
