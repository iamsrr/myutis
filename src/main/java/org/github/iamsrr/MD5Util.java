package org.github.iamsrr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Explain:
 * @Description:
 * @Author: Shihy
 * @Date: 2019/1/25 14:20
 */
public class MD5Util {

    public static String encode(String str) {
        MessageDigest md5 = null;
        String hexString = "";
        try {
            md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(str.getBytes());
            for (byte b : digest) {
                // 1将数据全部转换成正数：
                // 解释：为什么采用b&255
                /*
                 * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                 * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                 * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
                 * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
                 * 0000 0000 1001 1100 此时的temp是一个int类型的整数
                 */
                int temp = b & 255;
                // 2将所有的数据转换成16进制的形式
                // 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
                // 因此，需要对temp进行判断
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个"0"
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString;
    }

    public static boolean decode(String text, String md5Text) {
        return encode(text).equalsIgnoreCase(md5Text) ? true : false;
    }
}
