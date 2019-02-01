package com.ebook.beans.electronicstype;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gpj
 * @date 2018/12/21 10:35
 * @describe
 */

@Data
public class ElectronicsTypeQuery implements Serializable {
    private String id;
    private String name;
    private String[] ids;

}
