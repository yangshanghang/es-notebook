package com.icinfo.lpsp.notebook.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils extends com.icinfo.framework.tools.utils.DateUtils {

    /**
     * 字符串转为日期类型
     *
     * @param strDate 日期字符串
     * @param format 格式
     * @return 转换后的日期
     */
    public static Date stringToDate(String strDate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(strDate);
        } catch (Exception er) {
            return null;
        }
    }

    /**
     * 获得对应日期的00:00:00的时间
     *
     * @param startTime 时间字符串
     * @return 时间
     */
    public static Date getDateStart(String startTime) {
        if (startTime != null && startTime.length() > 0) {
            String[] tempArr = startTime.split(" ");
            return stringToDate(tempArr[0], "yyyy-MM-dd");
        }
        return null;
    }

    /**
     * 获得对应日期的23:59:59的时间
     *
     * @param endDate 时间字符串
     * @return 时间
     */
    public static Date getDateEnd(String endDate) {
        if (endDate != null && endDate.length() > 0) {
            String[] tempArr = endDate.split(" ");
            return stringToDate(tempArr[0] + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }

    /**
     * 计算两个时间相差多少个月
     *
     * @param startDate 时间参数
     * @param endDate   时间参数
     * @return 相差月份数. 如果为 0, 不代表两个时间月份相等, 而是两者相差不超过一个自然月.
     */
    public static int monthBetween(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal.setTime(endDate);

        // 根据年, 月 计算两个时间相差月份数
        int months = (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)) * 12
                + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);

        // 根据日, 纠正相差月份数
        if (endCal.get(Calendar.DATE) - startCal.get(Calendar.DATE) < 0) {
            months -= 1;
        }
        return months;
    }

    /**
     * 计算时间距今相隔多少个月
     *
     * @param past 时间参数
     * @return 相差月份数. 如果为 0, 不代表两个时间月份相等, 而是两者相差不超过一个自然月.
     */
    public static int monthBetween(Date past) {
        return monthBetween(past, new Date());
    }
}
