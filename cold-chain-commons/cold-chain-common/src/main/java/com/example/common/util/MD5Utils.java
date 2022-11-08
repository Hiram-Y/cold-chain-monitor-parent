package com.example.common.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public class MD5Utils {
    public static String sign(String content) {
        return DigestUtils.md5DigestAsHex(getContentBytes(content, "UTF-8"));
    }

    public static String sign(String content, String salt, String charset) {
        content = content + salt;
        return DigestUtils.md5DigestAsHex(getContentBytes(content, charset));
    }

    public static boolean verify(String content, String sign, String salt, String charset) {
        content = content + salt;
        String mySign = DigestUtils.md5DigestAsHex(getContentBytes(content, charset));

        return mySign.equals(sign);
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (!"".equals(charset)) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("MD5签名过程中出现错误, 指定的编码集错误");
            }
        } else {
            return content.getBytes();
        }
    }
}