package com.fxj.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BitCoinJsonRpcLink {

    @Autowired
    private JsonRpcHttpClient jsonRpcHttpClient;

    /**
     * 根据块高度查询块哈希
     * @param blockHeight
     * @return
     * @throws Throwable
     */
    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }

    /**
     * 查询交易池中交易哈希
     * @return
     * @throws Throwable
     */
    public JSONArray getMemPool () throws Throwable {
        return jsonRpcHttpClient.invoke("getrawmempool", new Object[]{false}, JSONArray.class);
    }

    /**
     * 获得创世区块哈希
     * @return
     * @throws Throwable
     */
    public String getBestBlockhash() throws Throwable {
        String bestblockhash = jsonRpcHttpClient.invoke("getbestblockhash", new Object[]{}, String.class);
        return bestblockhash;
    }

    /**
     * 根据交易id查询交易详细信息
     * @param txid
     * @return
     * @throws Throwable
     */
    public JSONObject getRawTransaxtion(String txid) throws Throwable {
        JSONObject rawTransaction = jsonRpcHttpClient.invoke("getrawtransaction", new Object[]{txid, true}, JSONObject.class);
        return rawTransaction;
    }

}
