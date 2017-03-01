package com.icinfo.lpsp.notebook.common.remote.client;

import com.icinfo.lpsp.notebook.common.util.JSONUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * json结果返回处理器
 * Created by yushunwei on 2016/8/7.
 */
public class JsonResponseHandler {

    /**
     * 构造函数
     *
     * @param requestInfo 请求信息
     * @param clazz       目标类
     * @param <T>         目标类对象
     * @return
     */
    public static <T> ResponseHandler<T> createResponseHandler(String requestInfo, final Class<T> clazz) {
        return new JsonResponseHandlerImpl<>(requestInfo, clazz);
    }

    /**
     * 内部类
     *
     * @param <T>
     */
    public static class JsonResponseHandlerImpl<T> extends BaseResponseHandler implements ResponseHandler<T> {

        /**
         * 类型自动转换
         */
        private Class<T> clazz;

        /**
         * 构造函数
         *
         * @param clazz 类型
         */
        public JsonResponseHandlerImpl(String requestInfo, Class<T> clazz) {
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
                String result = EntityUtils.toString(entity, Consts.UTF_8);
//                printResponse(result);
                return JSONUtils.parse(result, clazz);
            } else {
                printError("状态码错误:" + status);
                throw new ClientProtocolException("状态码错误:" + status);
            }
        }
    }
}
