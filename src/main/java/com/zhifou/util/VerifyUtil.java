package com.zhifou.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @program: NoteSystem
 * @description: verifyCreate
 * @author: 景光赞
 * @create: 2020-04-08 17:02
 **/
public class VerifyUtil {
    //邮箱字符串提取，去除了容易混淆的几个字符比如0,o~
    private final String SYMBOLS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private final Random RANDOM = new SecureRandom();
    /**
     * 生成4位随机验证码
     * @return 返回4位验证码
     */
    public String getVerify() {
        char[] nonceChars = new char[4];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
