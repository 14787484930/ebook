package com.model.utills.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Util {

    public static String sha1(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest digest = MessageDigest.getInstance("sha1");
            // 放入加密字符串
            digest.update(str.getBytes());
            // 进行加密
            byte[] digestMsg = digest.digest();
            // byte转换16进制
            for (byte b : digestMsg) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
}
