package com.fxj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private BitCoinApi bitCoinApi;

    @Autowired
    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    @GetMapping("/test")
    private String test() throws Throwable {
        //查询块链处理的各种状态信息
//        JSONObject chainInfo = bitCoinApi.getChainInfo();
//        System.out.println("查询块链处理的各种状态信息"+chainInfo);
//
//        //hash查询交易信息
//        JSONObject txByHash = bitCoinApi.getTxByHash("995d3606887ff1c3338129b3594b6bc8e4f7afd5758ad5b1b12c1ac8bed0ede5");
//        System.out.println("hash查询交易信息："+txByHash);
//
//        // hash查询块信息
//        JSONObject blockByHash = bitCoinApi.getBlockByHash("000000000000001bdad82ad2c4150edac9cd4e97aae05a3863ebf3195e445ddf");
//        System.out.println("hash查询块信息："+blockByHash);
//
//        //hash查询当前块上方块数量
//        JSONArray headersByHash = bitCoinApi.getHeadersByHash(10, "000000000000001bdad82ad2c4150edac9cd4e97aae05a3863ebf3195e445ddf");
//        System.out.println("hash查询当前块上方块数量"+headersByHash);
//
//        //height查询块信息
//        JSONObject blockByHeight = bitCoinApi.getBlockByHeight(1489456);
//        System.out.println("height查询块信息"+blockByHeight);
//
//        //查询可交易详细信息
//        JSONObject mempool = bitCoinApi.getMempoolInfo();
//        System.out.println("查询可交易详细信息"+mempool);
//
//        //查询交易可使用事务
//        JSONObject mempoolContents = bitCoinApi.getMempoolContents();
//        System.out.println("查询交易可使用事务"+mempoolContents);
//
//        //根据块高度查询块hash
//        String blockHashByHeight = bitCoinJsonRpcLink.getBlockHashByHeight(1489456);
//        System.out.println("根据块高度查询块hash"+blockHashByHeight);
        String hash = "000000000000ed8c150433d8c22d8d15490d27a7f60f5597aabb97a3e0144a66";
        JSONObject blockByHash = bitCoinApi.getBlockByHash(hash);
        JSONArray tx = blockByHash.getJSONArray("tx");
        if (tx!=null&&!tx.isEmpty()){
            for (int i = 0 ; i < tx.size() ; i++) {
                Map o = (Map) tx.get(i);
                String txid = (String) o.get("txid");
                JSONObject txByHash = bitCoinJsonRpcLink.getRawTransaxtion(txid);
                txByHash.put("time",new Date(txByHash.getLong("time")*1000));
                txByHash.put("blockhash",hash);
                System.out.println(txByHash);
            }
        }
        bitCoinJsonRpcLink.getMemPool();

        return "111";
    }

}
