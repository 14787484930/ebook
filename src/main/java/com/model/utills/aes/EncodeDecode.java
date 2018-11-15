package com.model.utills.aes;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * 肖济
 * 时间：2018-11-14
 * 用法：
 * AESEncode(加密规则，加密内容)
 * AESDEcode(加密规则，解密内容)
 *
 * 加密规则是一个字符串，由你自己指定
 * 加密规则需要一致，否则会出现错误；
 * 解密内容是加密后的字符串；
 *
 * 测试案例：
 * String a = EncodeDecode.AESEncode("123456","vxiao");
 *
 * String b = EncodeDecode.AESDecode("123456",a);
 * 则解密后的b的内容是 vxiao
 *
 * */
public class EncodeDecode {

    private static final String ENCODING = "UTF-8";
    private static final String KEY_ALGORITHM = "AES";//产生密钥的算法
    private static final int INITIAL_LENGTH = 256;//初始化密钥长度

    public static String AESEncode(String encodeRules, String content)throws InvalidKeyException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            BadPaddingException,
            UnsupportedEncodingException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(INITIAL_LENGTH, new SecureRandom(encodeRules.getBytes()));//初始化密钥长度,128,192,256（选用192和256的时候需要配置无政策限制权限文件--JDK6）
        SecretKey key = keyGenerator.generateKey();//产生密钥
        byte[] raw = key.getEncoded();
        SecretKey k = new SecretKeySpec(raw,KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        byte[] encodeByte = content.getBytes(ENCODING);
        byte[] AESByte = cipher.doFinal(encodeByte);
        String AESEncode = new String(new BASE64Encoder().encode(AESByte));
        return AESEncode;
    }
    public static String AESDecode(String encodeRules, String content)throws InvalidKeyException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            IOException,
            BadPaddingException,
            IllegalBlockSizeException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(INITIAL_LENGTH,new SecureRandom(encodeRules.getBytes()));

        SecretKey original_key = keyGenerator.generateKey();

        byte[] raw = original_key.getEncoded();

        SecretKey key = new SecretKeySpec(raw,KEY_ALGORITHM);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
        byte[] byteDecode = cipher.doFinal(byteContent);
        String AESDecode = new String(byteDecode,ENCODING);
        return AESDecode;
    }

}
