package com.icinfo.lpsp.notebook.common.remote.api;

import com.icinfo.framework.tools.utils.StringUtils;
import com.icinfo.lpsp.notebook.common.remote.client.HttpClient;
import com.icinfo.lpsp.notebook.common.remote.response.FileServerResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import java.io.File;

/**
 * 文件服务器API
 * Created by yushunwei on 2016/9/28.
 */
public class FileServerAPI extends BaseAPI {

    /**
     * 上传文件（包括图片，及附件）
     *
     * @param requestURL       图片上传接口地址
     * @param fileParaName     文件名参数
     * @param file             上传的文件
     * @param fileNameParaName 文件名参数
     * @param fileName         文件名
     * @param dirParaName      目录名参数
     * @param dirName          目录名
     * @return 上传文件结果json
     * @throws Exception
     */
    public static FileServerResponse fileUpload(String requestURL, String fileParaName,
                                                File file, String fileNameParaName, String fileName,
                                                String dirParaName, String dirName) throws Exception {
        // 设置多媒体请求参数
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .addPart(fileParaName, new FileBody(file))
                .addPart(fileNameParaName, new StringBody(fileName, ContentType.TEXT_PLAIN))
                .addPart(dirParaName, new StringBody(dirName, ContentType.TEXT_PLAIN));

        HttpPost httpPost = new HttpPost(requestURL);
        httpPost.setEntity(multipartEntityBuilder.build());

        // 拼接请求参数
        String requestInfo = StringUtils.assemblyString("\t", "uri:", requestURL, "\t",
                "fileParaName:", fileParaName, "\t",
                "fileNameParaName:", fileNameParaName, "\t",
                "fileName:", fileName, "\t",
                "dirParaName", dirParaName, "\t",
                "dirName", dirName);
        return HttpClient.executeJsonResult(httpPost, requestInfo, FileServerResponse.class);
    }

    /**
     * 上传图片(base64格式)文件
     *
     * @param requestURL   图片上传接口地址
     * @param fileParaName 文件名参数
     * @param file         上传的文件
     * @param dirParaName  目录名参数
     * @param dirName      目录名
     * @return 上传文件结果json
     * @throws Exception
     */
    public static FileServerResponse imageBase64Upload(String requestURL, String fileParaName, String file,
                                                       String dirParaName, String dirName) throws Exception {

        // 设置多媒体请求参数
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .addPart(fileParaName, new StringBody(","+file, ContentType.TEXT_PLAIN))
                .addPart(dirParaName, new StringBody(dirName, ContentType.TEXT_PLAIN));

        HttpPost httpPost = new HttpPost(requestURL);
        httpPost.setEntity(multipartEntityBuilder.build());

        // 拼接请求参数
        String requestInfo = StringUtils.assemblyString("\t", "uri:", requestURL, "\t",
                "fileParaName:", fileParaName, "\t",
                "file:", file, "\t",
                "dirParaName:", dirParaName, "\t",
                "dirName:", dirName);
        return HttpClient.executeJsonResult(httpPost, requestInfo, FileServerResponse.class);
    }

    /**
     * 图片下载
     * @param imageUrl 图片地址
     * @return 图片数据流
     * @throws Exception
     */
    public static byte[] imageDownLoad(String imageUrl) throws Exception{
        HttpGet httpGet = new HttpGet(imageUrl);
        // 拼接请求参数
        String requestInfo = StringUtils.assemblyString("\t", "uri:", imageUrl);
        return HttpClient.executeStreamResult(httpGet, requestInfo);
    }
}
