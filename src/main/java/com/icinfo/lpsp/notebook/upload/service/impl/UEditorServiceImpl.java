package com.icinfo.lpsp.notebook.upload.service.impl;

import com.icinfo.lpsp.notebook.common.constant.ConfigConstant;
import com.icinfo.lpsp.notebook.common.remote.api.FileServerAPI;
import com.icinfo.lpsp.notebook.common.remote.response.FileServerResponse;
import com.icinfo.lpsp.notebook.common.util.FileUtils;
import com.icinfo.lpsp.notebook.common.util.ImageUtils;
import com.icinfo.lpsp.notebook.common.util.StringUtils;
import com.icinfo.lpsp.notebook.upload.dto.UEditorUploadRltDto;
import com.icinfo.lpsp.notebook.upload.service.IUEditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件上传 Service
 * <p>
 * Created by wangxiao on 2016/11/17.
 */
@Service
public class UEditorServiceImpl implements IUEditorService {

    private Logger logger = LoggerFactory.getLogger(UEditorServiceImpl.class);

    // 文件(附件、图片、base64格式图片)上传接口地址
    @Value("${file.upload.url}")
    private String fileUploadUrl;

    /**
     * 上传图片文件
     *
     * @param upfile 图片文件
     * @return 上传结果
     * @throws Exception
     */
    @Override
    public UEditorUploadRltDto uploadImage(MultipartFile upfile) throws Exception {
        return uploadFile(upfile, fileUploadUrl + "doUpFileForKindEdit.do", ConfigConstant.fileServerImgDir);
    }

    /**
     * 上传涂鸦文件
     *
     * @param upfile 涂鸦文件
     * @return 上传结果
     * @throws Exception
     */
    @Override
    public UEditorUploadRltDto uploadScrawlImage(String upfile) throws Exception {
        UEditorUploadRltDto uploadRlt = new UEditorUploadRltDto();
        try {
            FileServerResponse picUploadResponse = FileServerAPI.imageBase64Upload(fileUploadUrl + "doUpFileForBase64Code.do", "base64ImageCode", upfile, "dir", ConfigConstant.fileServerImgDir);
            if (picUploadResponse.success()) {
                uploadRlt.setUrl(picUploadResponse.getUrl());
                uploadRlt.setState(UEditorUploadRltDto.SUCCESS_STATE);
            } else {
                logger.error("上传涂鸦失败，错误信息：{}", picUploadResponse.getMessage());
                uploadRlt.setState(UEditorUploadRltDto.FAIL_STATE);
            }
        } catch (Exception e) {
            logger.error("UEditor上传涂鸦失败！", e);
            uploadRlt.setState(UEditorUploadRltDto.FAIL_STATE);
        }
        return uploadRlt;
    }

    /**
     * 上传缩略图文件
     *
     * @param originImgUrl 原图地址
     * @param scaleWidth   缩小后宽度
     * @param scaleHeight  缩小后高度
     * @return 缩略图地址
     * @throws Exception
     */
    @Override
    public String uploadScaleImage(String originImgUrl, int scaleWidth, int scaleHeight) throws Exception {
        // 1.下载原图，并进行缩放
        File tempFile = FileUtils.createTempFile();
        ImageUtils.scale(new ByteArrayInputStream(FileServerAPI.imageDownLoad(originImgUrl)), scaleWidth, scaleHeight, new FileOutputStream(tempFile));
        // 2.执行上传
        return doUploadFile(tempFile, originImgUrl, fileUploadUrl + "doUpFileForKindEdit.do", ConfigConstant.fileServerImgDir);
    }

    /**
     * 上传附件
     *
     * @param upfile 附件文件
     * @return 上传结果
     * @throws Exception
     */
    @Override
    public UEditorUploadRltDto uploadAttachment(MultipartFile upfile) throws Exception {
        return uploadFile(upfile, fileUploadUrl + "doUpEnclosureFile.do", ConfigConstant.fileServerAttaDir);
    }

    /**
     * 上传文件（包括图片及附件）
     *
     * @param upfile    文件
     * @param uploadUrl 上传地址
     * @param dir       上传目录
     * @return 上传结果
     * @throws Exception
     */
    private UEditorUploadRltDto uploadFile(MultipartFile upfile, String uploadUrl, String dir) throws Exception {
        UEditorUploadRltDto uploadRlt = new UEditorUploadRltDto();
        File tempFile = FileUtils.createTempFile();
        upfile.transferTo(tempFile);
        String fileName = upfile.getOriginalFilename();
        // 执行上传
        String url = doUploadFile(tempFile, fileName, uploadUrl, dir);
        if (StringUtils.isNotBlank(url)) {
            uploadRlt.setUrl(url);
            uploadRlt.setOriginal(fileName);
            uploadRlt.setState(UEditorUploadRltDto.SUCCESS_STATE);
        } else {
            uploadRlt.setState(UEditorUploadRltDto.FAIL_STATE);
        }
        return uploadRlt;
    }

    /**
     * 执行文件上传（包括图片及附件）
     *
     * @param tempFile  需要上传的文件
     * @param fileName  文件名称
     * @param uploadUrl 上传地址
     * @param dir       上传目录
     * @return 上传成功后的文件地址
     * @throws Exception
     */
    private String doUploadFile(File tempFile, String fileName, String uploadUrl, String dir) throws Exception {
        try {
            FileServerResponse picUploadResponse = FileServerAPI.fileUpload(
                    uploadUrl,
                    "file",
                    tempFile,
                    "filename",
                    fileName,
                    "dir", dir);
            if (picUploadResponse.success()) {
                return picUploadResponse.getUrl();
            } else {
                logger.error("上传文件失败，错误信息：{}", picUploadResponse.getMessage());
            }
        } catch (Exception e) {
            logger.error("UEditor上传文件异常！", e);
        } finally {
            // 临时文件删除
            FileUtils.deleteFile(tempFile);
        }
        return null;
    }
}
