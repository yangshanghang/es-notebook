package com.icinfo.lpsp.notebook.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 可配置常量
 * Created by wangxiao on 2016/1/10.
 */
@Component
@Lazy(false)
public class ConfigConstant {

    /**
     * 文件服务器联连公共服务平台图片目录
     */
    public static String fileServerImgDir;

    /**
     * 文件服务器联连公共服务平台附件目录
     */
    public static String fileServerAttaDir;

    @Value("${file.upload.img.dir}")
    public void setFileServerImgDir(String fileServerImgDir) {
        ConfigConstant.fileServerImgDir = fileServerImgDir;
    }

    @Value("${file.upload.atta.dir}")
    public void setFileServerAttaDir(String fileServerAttaDir) {
        ConfigConstant.fileServerAttaDir = fileServerAttaDir;
    }
}
