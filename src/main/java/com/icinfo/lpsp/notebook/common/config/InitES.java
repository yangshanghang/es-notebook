package com.icinfo.lpsp.notebook.common.config;


import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：初始化连接es服务端
 */
@Configuration
public class InitES {

    /**
     * es服务器地址
     */
    @Value("${es.connection.url}")
    private String connectionUrl;

    /**
     * 配置jest客户端,到时使用spring时,可以用配置方式 ,现在暂时使用new ...
     *
     * @return
     */
    @Bean
    public HttpClientConfig httpClientConfig() {
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder(connectionUrl).multiThreaded(true).build();
        return httpClientConfig;
    }

    /**
     * 描述：获取一个jest的对象
     *
     * @return
     */
    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(httpClientConfig());
        return factory.getObject();
    }
}
