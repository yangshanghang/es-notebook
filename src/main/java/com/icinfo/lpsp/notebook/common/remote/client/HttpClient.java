package com.icinfo.lpsp.notebook.common.remote.client;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

/**
 * Http请求客户端
 * Created by yushunwei on 2016/8/7.
 */
public class HttpClient {

    /**
     * 请求超时配置
     */
    private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).setSocketTimeout(5 * 1000).build();

    /**
     * 执行json格式返回类请求
     * @param request 请求
     * @param requestInfo 请求信息
     * @param clazz 返回对象类型
     * @param <T> 泛型
     * @return 泛型对象
     * @throws Exception
     */
    public static <T> T executeJsonResult(HttpUriRequest request,String requestInfo, Class<T> clazz) throws Exception{
        return execute(request, JsonResponseHandler.createResponseHandler(requestInfo,clazz));
    }

    /**
     * 发送请求
     * @param request 请求
     * @param responseHandler 响应处理器
     * @param <T> 泛型
     * @return 泛型对象
     * @throws Exception
     */
    private static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        return httpClient.execute(request,responseHandler);
    }

    /**
     * 执行数据流格式返回类请求
     * @param request 请求
     * @param requestInfo 请求信息
     * @return 数据流
     * @throws Exception
     */
    public static byte[] executeStreamResult(HttpUriRequest request,String requestInfo) throws Exception{
        return execute(request, StreamResponseHandler.createResponseHandler(requestInfo,byte[].class));
    }
}
