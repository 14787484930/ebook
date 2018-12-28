package com.model.utills.senseword;
/**
 * 敏感词过滤用到Pattern和Matcher两个类
 * 封装在isSensive方法中，调用方法：
 *
 * isSensive('原字符','目标字符串')
 * 表达的意思是元字符是否在目标字符串中
 *
 * readFile是将敏感词存储在文件中，读取出来
 * 返回值是一个列表，调用时传入文件路径
 */

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileterWord {
   public static boolean isSensive(String ch, String target) {
        Pattern p = Pattern.compile(ch);
        Matcher m = p.matcher(target);
       return m.find();


    }

    public static ArrayList<String> readFile(String filePath) throws IOException {

        File f = new File(filePath);
        String line ;

        InputStreamReader read = new InputStreamReader(new FileInputStream(f),"GBK");
        BufferedReader br = new BufferedReader(read);

        ArrayList<String> listWord = new ArrayList<>();
        while((line = br.readLine() )!= null) {
            listWord.add(line);
        }
        read.close();

        return listWord;

    }
}
