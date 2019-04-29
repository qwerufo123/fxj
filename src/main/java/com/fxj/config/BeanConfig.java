package com.fxj.config;

import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.mapper.BlockMapper;
import com.fxj.mapper.TransactionMapper;
import com.fxj.service.SynchronizationDataService;
import com.fxj.service.impl.SynchronizationDataImpl;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BitCoinApi bitCoinApi;

    @Autowired
    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

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

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Bean
    private Runnable newRunable() throws Throwable {
        blockMapper.truncate();
        transactionMapper.truncate();
        JSONObject chainInfo = bitCoinApi.getChainInfo();
        int height = (int) chainInfo.get("blocks");
        SynchronizationDataImpl synchronizationData = new SynchronizationDataImpl(bitCoinApi, bitCoinJsonRpcLink, blockMapper, transactionMapper, height);
//        synchronizationData.start();
        return null;
    }

}
