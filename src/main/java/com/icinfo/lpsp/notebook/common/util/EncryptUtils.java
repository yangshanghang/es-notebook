package com.icinfo.lpsp.notebook.common.util;

import com.icinfo.framework.tools.utils.Md5Utils;
import com.icinfo.lpsp.notebook.system.model.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 加密工具
 */
public class EncryptUtils extends Md5Utils {

    // 随机数生成器
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    // 密码hash算法
    private static final String PASSWORD_HASH_ALGORITHM_NAME = "md5";

    // 密码迭代次数
    private static int PASSWORD_HASH_ITERATIONS = 2;

    /**
     * 密码加密
     *
     * @param password 原始密码
     * @param salt     hash盐值
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(PASSWORD_HASH_ALGORITHM_NAME, password,
                ByteSource.Util.bytes(salt), PASSWORD_HASH_ITERATIONS)
                .toHex();
    }

    /**
     * 用户密码加密
     *
     * @param user 用户信息
     */
    public static void encryptPassword(User user) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setEncryptSalt(salt);
        user.setPassword(encryptPassword(user.getPassword(),
                user.getEncryptSalt()));
    }

    /**
     * md5哈希
     *
     * @param str 源信息
     * @return 哈希值
     */
    public static String md5Encoder(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] strChars = str.toCharArray();
            byte[] strBytes = new byte[strChars.length];
            for (int i = 0; i < strChars.length; i++)
                strBytes[i] = (byte) strChars[i];
            byte[] md5Bytes = md5.digest(strBytes);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 描述；生成哈希
     *
     * @return
     * @throws Exception
     */
    public static String randomNumber() {
        String salt = randomNumberGenerator.nextBytes().toHex();
        return md5Encoder(salt);
    }

    /**
     * Base64 编码
     *
     * @param str     源信息
     * @param charset 编码字符集
     * @return 编码结果
     * @throws UnsupportedEncodingException
     */
    public static String base64Encoder(String str, String charset) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(charset)) {
            charset = "utf-8";
        }
        return new String(Base64.encodeBase64(str.getBytes()), charset);
    }

    /**
     * Base64 解码
     *
     * @param str     源信息
     * @param charset 解码字符集
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String base64Decoder(String str, String charset) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(charset)) {
            charset = "utf-8";
        }
        return new String(Base64.decodeBase64(str), charset);
    }

    public static void main(String[] args) {
        System.out.println(randomNumber());
    }
}