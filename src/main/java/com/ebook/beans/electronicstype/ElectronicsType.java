package com.ebook.beans.electronicstype;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gpj
 * @date 2018/12/21 10:30
 * @describe
 */

@Data
public class ElectronicsType implements Serializable {
    private String id;
    private String name;
    private String des;
}
