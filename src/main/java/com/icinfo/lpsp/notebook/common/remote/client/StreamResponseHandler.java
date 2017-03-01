package com.icinfo.lpsp.notebook.common.remote.client;

import com.icinfo.lpsp.notebook.common.remote.api.FileServerAPI;
import com.icinfo.lpsp.notebook.common.util.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * 数据流结果返回处理器
 * Created by yushunwei on 2016/11/19.
 */
public class StreamResponseHandler {
    /**
     * 构造函数
     *
     * @param requestInfo 请求信息
     * @return
     */
    public static <T> ResponseHandler<T> createResponseHandler(String requestInfo,final Class<T> clazz) {
        return new StreamResponseHandler.StreamResponseHandlerImpl(requestInfo,clazz);
    }

    /**
     * 内部类
     *
     */
    public static class StreamResponseHandlerImpl<T> extends BaseResponseHandler implements ResponseHandler<T> {
        /**
         * 类型自动转换
         */
        private Class<T> clazz;

        /**
         * 构造函数
         *
         */
        public StreamResponseHandlerImpl(String requestInfo, Class<T> clazz) {
            this.requestInfo = requestInfo;
            this.clazz = clazz;
        }

        /**
         * 接口响应处理
         *
         * @param response 接口响应
         * @return clazz类型对象
         * @throws IOException
         */
        @Override
        public T handleResponse(HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            // http返回状态判断
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int ch = 0;
                while ((ch = response.getEntity().getContent().read(buffer)) != -1) {
                    out.write(buffer,0,ch);
                }
                return (T)out.toByteArray();
            } else {
                printError("状态码错误:" + status);
                throw new ClientProtocolException("状态码错误:" + status);
            }
        }
    }

    public static void main(String[] args) {
        try {
            byte[] in = FileServerAPI.imageDownLoad("http://192.168.1.247:9001/upload/linksgood-image/20161118//20161118134630_697.png");
            File file = new File("D:/dest1.png");
            ImageUtils.scale(new ByteArrayInputStream(in), 120, 90, new FileOutputStream(file));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
