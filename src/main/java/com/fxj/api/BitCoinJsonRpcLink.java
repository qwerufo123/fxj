package com.fxj.api;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BitCoinJsonRpcLink {

    @Autowired
    private JsonRpcHttpClient jsonRpcHttpClient;

    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }

}
