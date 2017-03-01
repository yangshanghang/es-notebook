package com.icinfo.lpsp.notebook.upload.service;

import com.icinfo.lpsp.notebook.upload.dto.UEditorUploadRltDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangxiao on 2016/11/17.
 */
public interface IUEditorService {

    /**
     * 上传图片
     *
     * @param upfile 图片文件
     * @return 上传结果
     * @throws Exception
     */
    UEditorUploadRltDto uploadImage(MultipartFile upfile) throws Exception;

    /**
     * 上传涂鸦
     *
     * @param upfile 涂鸦文件
     * @return 上传结果
     * @throws Exception
     */
    UEditorUploadRltDto uploadScrawlImage(String upfile) throws Exception;

    /**
     * 上传缩略图文件
     * @param originImgUrl 原图地址
     * @param scaleWidth 缩小后宽度
     * @param scaleHeight 缩小后高度
     * @return 缩略图地址
     * @throws Exception
     */
    String uploadScaleImage(String originImgUrl,int scaleWidth,int scaleHeight) throws Exception;

    /**
     * 上传附件文件
     * @param upfile 附件文件
     * @return 上传结果
     * @throws Exception
     */
    UEditorUploadRltDto uploadAttachment(MultipartFile upfile) throws Exception;
}
