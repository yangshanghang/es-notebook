package com.icinfo.lpsp.notebook.common.util;

import com.icinfo.framework.tools.utils.HttpClientUtil;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求工具封装.
 */
public class HttpUtils extends HttpClientUtil {
    /**
     * 获取 request 中所有参数
     *
     * @param request http请求
     * @return map形式参数集合
     */
    public static Map<String, Object> getAllParam(HttpServletRequest request) {
        Map<String, Object> tempMap = new HashMap<>();
        Enumeration enuParam = request.getParameterNames();
        while (enuParam.hasMoreElements()) {
            String paramName = (String) enuParam.nextElement();
            String[] arrParamValue = request.getParameterValues(paramName);
            String paramValue = StringUtils.arrayToString(arrParamValue, ",");
            // 过滤掉空值
            if (paramValue != null && paramValue.length() > 0) {
                tempMap.put(paramName, paramValue);
                if (paramName.endsWith("Time") || paramName.endsWith("TimeStart") || paramName.endsWith("TimeEnd")) {
                    tempMap.put(paramName, DateUtils.stringToDate(paramValue, "yyyy-MM-dd HH:mm:ss"));
                }
                if (paramName.endsWith("DateStart")) {
                    tempMap.put(paramName, DateUtils.getDateStart(paramValue));
                }
                if (paramName.endsWith("DateEnd")) {
                    tempMap.put(paramName, DateUtils.getDateEnd(paramValue));
                }
                if (paramName.endsWith("Date")) {
                    tempMap.put(paramName, DateUtils.stringToDate(paramValue, "yyyy-MM-dd"));
                }
            }
        }
        return tempMap;
    }

    /**
     * 判断是否Ajax请求
     *
     * @param request 请求
     * @return ajax请求：true 否则 false
     */
    public static boolean isAjax(HttpServletRequest request) {
        // 判定是否为异步请求，非异步请求直接进入error页面
        return (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
    }

    /**
     * 异步请求异常处理
     * @param response 请求响应
     * @param errorInfo 错误信息
     * @param httpStatus http状态码
     * @throws Exception
     */
    public static void writeError(HttpServletResponse response, String errorInfo,int httpStatus) throws Exception {
        PrintWriter writer = null;
        try {
            // 设置响应字符集
            response.setContentType("text/html; charset=utf-8");
            // 设置HTTP响应状态码
            response.setStatus(httpStatus);
            writer = response.getWriter();
            writer.write(errorInfo);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
