package com.model.utills.randomnum;

import java.util.Random;

/**
 * @author zxl
 * @date 2019/4/8 11:52
 * @describe
 */
public class RandomNum {

    public static String getRandomNumer(){

        Random random = new Random();
        String code = "";
        for(int i = 0;i<4;i++){
            code += random.nextInt(10);
        }
        return code;
    }

    public static void main(String[] args) {

        for(int i=0 ;i<6;i++){
            System.out.println(getRandomNumer());
        }
    }
}
