package com.fxj.config;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;

@Component
//读取配置区块链链接数据信息
@PropertySource("classpath:BlockchaineClint.properties")
public class BeanConfig {

    @Value("${bitCoin.user}")
    private String user;

    @Value("${bitCoin.pwd}")
    private String pwd;

    @Value("${bitCoin.url}")
    private String url;

    /**
     * 创建JsonRpcHttpClient类
     * @return
     * @throws MalformedURLException
     */
    @Bean
    private JsonRpcHttpClient getJsonRpcHttpClint() throws MalformedURLException {
        String format = String.format("%s:%s", user, pwd);
        String str = Base64.getEncoder().encodeToString(format.getBytes());
        String autoString = String.format("Basic %s", str);
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization",autoString);
        return new JsonRpcHttpClient(new URL(url),map);
    }

}
