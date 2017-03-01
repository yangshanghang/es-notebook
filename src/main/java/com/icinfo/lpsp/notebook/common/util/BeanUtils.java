package com.icinfo.lpsp.notebook.common.util;

/**
 * BeanUtils 工具类
 *
 * Created by wangxiao on 2016/11/10.
 */
public class BeanUtils extends com.icinfo.framework.tools.utils.BeanUtils {

    /**
     * 使用 BeanUtils 将 srcObj 转换成为 destClass 类的对象
     *
     * @param destClass 目标类
     * @param srcObj    源对象
     * @return 转换后的对象
     * @throws Exception
     */
    public static <T, E> T convert(Class<T> destClass, E srcObj) throws Exception {
        T destObj = destClass.newInstance();
        com.icinfo.framework.tools.utils.BeanUtils.copyProperties(destObj, srcObj);
        return destObj;
    }
}
