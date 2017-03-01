package com.icinfo.lpsp.notebook.common.remote.response;

import java.io.Serializable;

/**
 * 图片上传接口返回
 * Created by yushunwei on 2016/9/28.
 */
public class FileServerResponse implements Serializable{

    private static final long serialVersionUID = -6707671896909545507L;

    private int successCode = 0;

    // 错误码
    private int error;

    // 图片地址
    private String url;

    // 错误信息
    private String message;

    /**
     * 成功判断
     * @return
     */
    public boolean success(){
        return successCode == error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
