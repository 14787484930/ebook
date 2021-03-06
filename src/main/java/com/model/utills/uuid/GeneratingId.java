package com.model.utills.uuid;

import java.util.UUID;

/**
 * zxl
 * 2018-11-15
 * 用uuid生成独一无二的id
 */
public class GeneratingId {

    //生成id的工具方法
    public static final String getId(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //生成四位随机数
    public static final int getRandomNumber(){

        return (int)Math.ceil((Math.random()*9+1)*1000);

    }

    public static void main(String[] args) {

        System.out.println(getId());
    }
}
