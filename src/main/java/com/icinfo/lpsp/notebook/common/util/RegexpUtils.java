package com.icinfo.lpsp.notebook.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 * Created by yushunwei on 2016/11/19.
 */
public class RegexpUtils {
    /**
     * 获取UEditor文本内容中第一张图片的地址
     * @param content UEditor文本内容
     * @param pathKeyWords 路径关键字
     * @return 地址
     */
    public static String getFirstImgUrlFromUeditor(String content,String pathKeyWords) {
        String patternExp = "&lt;img\\s*src=&quot;\\s*(http.+?)\\s*&quot;";
        Pattern pattern = Pattern.compile(patternExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()){
            // 返回第一张匹配到路径关键字的图片
            if(matcher.group(1).contains(pathKeyWords)){
                return matcher.group(1);
            }
        }
        return null;
    }
}
