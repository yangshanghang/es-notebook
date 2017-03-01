package com.icinfo.lpsp.notebook.common.util;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils extends com.icinfo.framework.tools.utils.StringUtils {

    /**
     * 数组转成字符串
     *
     * @param arrObject 数组对象
     * @param separator 分隔符
     * @return 组装后的字符串
     */
    public static String arrayToString(Object[] arrObject, String separator) {
        // 默认为","号
        if (separator == null || separator.equals(""))
            separator = ",";

        String returnStrValue = "";

        if (arrObject != null) {
            for (Object object : arrObject) {
                if (returnStrValue.equals("")) {
                    returnStrValue = String.valueOf(object);
                } else {
                    returnStrValue = returnStrValue + separator
                            + String.valueOf(object);
                }
            }
        }
        return returnStrValue;
    }

    /**
     * 身份证15位转18位
     *
     * @param idNo        身份证号
     * @param centuryYear 世纪年数
     * @return 转换后的身份证
     */
    public static String idNo15To18(String idNo, int centuryYear) {
        int[] weight = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
                8, 4, 2, 1};
        String idNo18 = idNo.substring(0, 6)
                + (Integer.valueOf(idNo.substring(6, 8)) >= centuryYear ? "19" : "20")
                + idNo.substring(6);
        int checkSum = 0;
        for (int i = 0; i < 17; i++) {
            int ai = Integer.parseInt("" + idNo18.charAt(i)); // 位于 i 位置的数值
            checkSum = checkSum + ai * weight[i];
        }

        int checkNum = checkSum % 11;
        String checkChar = null;

        switch (checkNum) {
            case 0:
                checkChar = "1";
                break;
            case 1:
                checkChar = "0";
                break;
            case 2:
                checkChar = "X";
                break;
            default:
                checkChar = "" + (12 - checkNum);
        }

        return idNo18 + checkChar;
    }

    /**
     * 判断身份证是否满足正则表达式
     *
     * @param idNo 身份证号
     * @return true:符合身份证号格式
     */
    public static boolean isIdNo(String idNo) {
        return Pattern.matches("/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/", idNo);
    }
}
