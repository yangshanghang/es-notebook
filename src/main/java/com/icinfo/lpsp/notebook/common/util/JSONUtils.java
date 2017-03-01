package com.icinfo.lpsp.notebook.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * json 转换工具
 */
public class JSONUtils {
    /**
     * json反序列化
     *
     * @param result 转换源
     * @param clazz  目标对象
     * @param <T>    泛型
     * @return 泛型对象
     * @throws Exception
     */
    public static <T> T parse(String result, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 自定义时间格式转换
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
        return mapper.readValue(result, clazz);
    }

    /**
     * json序列化
     *
     * @param t   目标对象
     * @param <T> 泛型
     * @return 序列化结果
     * @throws Exception
     */
    public static <T> String parse(T t) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(t);
    }
}
