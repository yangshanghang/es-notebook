package com.icinfo.lpsp.notebook.upload.dto;

/**
 * 百度UEditor文件上传结果Dto
 * Created by yushunwei on 2016/11/17.
 */
public class UEditorUploadRltDto {
    // 上传成功状态
    public static final String SUCCESS_STATE = "SUCCESS";

    // 上传失败状态
    public static final String FAIL_STATE = "上传失败";

    // 上传结果
    private String state;

    // 原始文件名称
    private String original;

    // 文件大小
    private String size;

    // 标题
    private String title;

    // 文件后缀类型
    private String type;

    // 文件地址
    private String url;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
