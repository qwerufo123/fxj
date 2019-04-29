package com.fxj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.mapper.BlockMapper;
import com.fxj.mapper.TransactionMapper;
import com.fxj.service.SynchronizationDataService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

public class SynchronizationDataImpl extends Thread implements SynchronizationDataService {

    private BitCoinApi bitCoinApi;

    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    private BlockMapper blockMapper;

    private TransactionMapper transactionMapper;

    private Integer height;

    public SynchronizationDataImpl(BitCoinApi bitCoinApi, BitCoinJsonRpcLink bitCoinJsonRpcLink, BlockMapper blockMapper, TransactionMapper transactionMapper,Integer height) {
        this.bitCoinApi = bitCoinApi;
        this.bitCoinJsonRpcLink = bitCoinJsonRpcLink;
        this.blockMapper = blockMapper;
        this.transactionMapper = transactionMapper;
        this.height = height;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++){
            try {
                SynchronizationByBlockHeight(height--);
            } catch (Throwable throwable) {
                continue;
            }

        }
    }

    @Override
    public void SynchronizationByBlockHeight(int height) throws Throwable {
        String blockHashByHeight = bitCoinJsonRpcLink.getBlockHashByHeight(height);
        SynchronizationByblockHash(blockHashByHeight);
    }

    public void SynchronizationByblockHash(String hash) throws Throwable {
        JSONObject blockByHash = bitCoinApi.getBlockByHash(hash);
        blockByHash.put("time",new Date(blockByHash.getLong("time")*1000));
        blockMapper.insertSelective(blockByHash);
        JSONArray tx = blockByHash.getJSONArray("tx");
        if (tx!=null&&!tx.isEmpty()){
            for (int i = 0 ; i < tx.size() ; i++) {
                Map o = (Map) tx.get(i);
                String txid = (String) o.get("txid");
                JSONObject txByHash = bitCoinJsonRpcLink.getRawTransaxtion(txid);
                txByHash.put("time",new Date(txByHash.getLong("time")*1000));
                txByHash.put("blockhash",hash);
                transactionMapper.insertSelective(txByHash);
            }
        }
    }


}
