package com.ebook.beans.electronicstype;

import com.ebook.beans.base.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gpj
 * @date 2018/12/21 10:35
 * @describe
 */

@Data
public class ElectronicsTypeQuery extends BaseQuery {

    private String electronicsName; //电子名称
    private Integer electronicsType; //电子类型
    private Integer hasInvoice; //(0:没有,1:有)

}
